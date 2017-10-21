package com.imooc.web.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.UserQueryCondition;
import com.imooc.exception.UserNotException;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public User getInfo(@PathVariable  String id) throws Exception {

        throw new UserNotException(id);

//        User user = new User();
//        user.setUsername("tom");
//        return user;
    }

    @PostMapping
    public User create(@RequestBody User user){
        System.out.println(ReflectionToStringBuilder.toString(user));
        System.out.println("controller:" + user.getBirthday());
        user.setId(1);
        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User update( @RequestBody @Valid User user/*,BindingResult errors*/){

//        if (errors.hasErrors()){
//
//            errors.getAllErrors().stream().forEach(error -> {
//
//                FieldError fieldError=(FieldError) error;
//                String message=fieldError.getField() + error.getDefaultMessage();
//                System.err.println(message);
//            });
//        }


        System.out.println(ReflectionToStringBuilder.toString(user));
        user.setId(1);
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete( @PathVariable String id){

        System.out.println(id);
    }
}
