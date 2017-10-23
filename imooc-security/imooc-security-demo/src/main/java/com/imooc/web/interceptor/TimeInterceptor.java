package com.imooc.web.interceptor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by zkr on 2017/10/23.
 */

@Component("qwe") //只有这个注释不能使 拦截器起作用，还需要在webConfig中配置， webConfig需要继承WebMvcConfigurerAdapter
public class TimeInterceptor implements HandlerInterceptor{

    //控制器方法被执行之前，被调用
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {

       System.out.println("interceptor preHandle");
       System.out.println( ((HandlerMethod)handler).getBean().getClass().getName() );
       System.out.println( ((HandlerMethod)handler).getMethod().getName() );

       httpServletRequest.setAttribute("startTime",new Date().getTime());
       return true;   //true false控制是否调用后面的controller方法
    }

    //控制器方法被执行之后，被调用，如果 Controller方法抛出了异常，这个方法，将不被调用
    //相反下的afterCompletion()方法，一定会被调用
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

        System.out.println("interceptor postHandle");
        Long start = (Long) httpServletRequest.getAttribute("startTime");
        System.out.println("interceptor run time耗时" + (new Date().getTime()-start));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

        System.out.println("interceptor afterCompletionHandle");
        Long start = (Long) httpServletRequest.getAttribute("startTime");
        System.out.println("afterCompletion run time耗时" + (new Date().getTime()-start));
        System.out.println("exception is" + e);
    }
}
