package com.imooc.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zkr on 2017/10/29.
 */
@RestController
@RequestMapping("/authen")
public class CodeReaderExeciseController {

    @GetMapping("/me")
    public Object getCurrentUser(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/mesimple")  //springMvn会自动设置Authentication authentication
    public Object getCurrentUserSimple(Authentication authentication){
        return authentication;
    }

    @GetMapping("/medetail")  //springMvn会自动设置Authentication authentication
    public Object getCurrentUserSimple(@AuthenticationPrincipal UserDetails authentication){
        return authentication;
    }


}
