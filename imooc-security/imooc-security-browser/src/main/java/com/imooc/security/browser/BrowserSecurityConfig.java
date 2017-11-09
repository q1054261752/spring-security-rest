package com.imooc.security.browser;

import com.imooc.security.browser.authentication.ImoocAuthenticationFailureHandler;
import com.imooc.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.SmsCodeFilter;
import com.imooc.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by zkr on 2017/10/25.
 */
@Component
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;  //设置自定义成功处理器
    @Autowired
    private ImoocAuthenticationFailureHandler imoocAuthenticationFailureHandler;  //设置自定义失败处理器

    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){  //记住我功能

        JdbcTokenRepositoryImpl tokenRepository=new JdbcTokenRepositoryImpl();  //进去有一个 CREATE_TABLE_SQL
        tokenRepository.setDataSource(dataSource);
        tokenRepository.setCreateTableOnStartup(false);
        return tokenRepository;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();//图形验证码过滤器
        validateCodeFilter.setAuthenticationFailureHander(imoocAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();//图形验证码过滤器
        smsCodeFilter.setAuthenticationFailureHander(imoocAuthenticationFailureHandler);
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();

//        http.httpBasic().
        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
              .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
             .formLogin()
             .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")   //自定义 登录url
                .successHandler(imoocAuthenticationSuccessHandler)  //设置自定义成功处理器
                .failureHandler(imoocAuthenticationFailureHandler) //设置自定义失败处理器
                .and()
             .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
             .authorizeRequests()
             .antMatchers("/authentication/require",
                     securityProperties.getBrowser().getLoginPage()
                     ,"/code/*").permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().disable()   // 禁用默认开启的跨站请求伪造
              .apply(smsCodeAuthenticationSecurityConfig);
    }

    //密码加密类配置
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
