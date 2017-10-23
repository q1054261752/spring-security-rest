package com.imooc.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * Created by zkr on 2017/10/23.
 *
 * 这是第三方的filter , 不能通过 @Compont注入，通过 WebConfig配置
 */
public class ThirdPartyFiler implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        System.out.println("third party filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("third party filter  start");
        long start = new Date().getTime();
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("thrid party filter run time耗时" + (new Date().getTime()-start));
        System.out.println("third party filter  finish");
    }

    @Override
    public void destroy() {
        System.out.println("third party filter  destroy");
    }
}
