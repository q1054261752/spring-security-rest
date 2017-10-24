package com.imooc.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by zkr on 2017/10/23.
 */
//@Aspect    //为什么要写切片了，因为interceptor是拿不到Controller方法的参数的
//@Component
public class TimeAspect {

    @Around("execution(* com.imooc.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("time aspect start");
        long start = new Date().getTime();

        Object[] args = pjp.getArgs();
        for (Object arg:args){

            System.out.println("args is " + arg);
        }

        Object object = pjp.proceed();

        System.out.println("time aspect run time耗时" + (new Date().getTime()-start));
        System.out.println("time aspect end");


        return object;
    }
}
