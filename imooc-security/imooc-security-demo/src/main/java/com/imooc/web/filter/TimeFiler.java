package com.imooc.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Date;

/**
 * Created by zkr on 2017/10/23.
 */
//filter的局限性  只能拿到 http的请求和响应，在javaEE规范中并不知道spring相关的东西，并不知道不那个Controller处理的请求，如果，需要Controller的信息，那就用Interceptor拦截器
//@Component   //通过注解增加 filter 默认过滤所有的Controller路径
//@WebFilter(filterName="myFilter",urlPatterns="/*")    //需要在springBoot启动类上加@ServletComponentScan注解，同在可以用@WebListener，
public class TimeFiler implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("time filter start");
        long start = new Date().getTime();
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("time filter run time耗时" + (new Date().getTime()-start));
        System.out.println("time filter finish");
    }

    @Override
    public void destroy() {
        System.out.println("time filter destroy");
    }
}
