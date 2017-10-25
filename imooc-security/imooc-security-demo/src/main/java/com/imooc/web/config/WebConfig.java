package com.imooc.web.config;

import com.imooc.web.filter.ThirdPartyFiler;
import com.imooc.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zkr on 2017/10/23.
 */
//@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

//    @Qualifier("qwe")
//    @Autowired
    @Resource(name = "qwe")
    private TimeInterceptor timeInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }

    @Bean
    public FilterRegistrationBean thirdPartyClass(){

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        ThirdPartyFiler thirdPartyFiler = new ThirdPartyFiler();
        registrationBean.setFilter(thirdPartyFiler);
        List<String> list = new ArrayList<>();
        list.add("/*");
        registrationBean.setUrlPatterns(list);
        return registrationBean;
    }
}
