package com.imooc.web.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.UserQueryCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zkr on 2017/10/19.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping()
    @JsonView(User.UserSimpleView.class)
    public List<User> query(UserQueryCondition condition,@PageableDefault(page = 2,size = 17,sort = "username,desc") Pageable pageable){

        System.out.println(ReflectionToStringBuilder.toString(condition));

        System.out.println(ReflectionToStringBuilder.toString(pageable));

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable  String id){

        User user = new User();
        user.setUsername("tom");
        return user;
    }

    @PostMapping
    public User create(@RequestBody User user){
        System.out.println(ReflectionToStringBuilder.toString(user));
        System.out.println("controller:" + user.getBirthday());
        user.setId(1);
        return user;
    }
}
