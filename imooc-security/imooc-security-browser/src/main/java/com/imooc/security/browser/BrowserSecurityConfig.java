package com.imooc.security.browser;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by zkr on 2017/10/25.
 */
@Component

public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic().
        http.formLogin()
             .loginPage("/imooc-signIn.html")
                .loginProcessingUrl("/authentication/form")   //自定义 登录url
             .and()
             .authorizeRequests()
             .antMatchers("/imooc-signIn.html").permitAll()
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
