package com.imooc.security.browser;

import com.imooc.security.browser.authentication.ImoocAuthenticationFailureHandler;
import com.imooc.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic().
        http.formLogin()
             .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")   //自定义 登录url
                .successHandler(imoocAuthenticationSuccessHandler)  //设置自定义成功处理器
                .failureHandler(imoocAuthenticationFailureHandler) //设置自定义失败处理器
             .and()
             .authorizeRequests()
             .antMatchers("/authentication/require",
                     securityProperties.getBrowser().getLoginPage()).permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().disable();   // 禁用默认开启的跨站请求伪造
    }

    //密码加密类配置
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
