package com.imooc.validator;

import com.imooc.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by zkr on 2017/10/21.
 */
//@Component 不用加，见到ConstraintValidator，自动导入
public class MyConstrantValidator implements ConstraintValidator<MyConstrant,Object>{

    @Autowired
    private HelloService helloService;

    @Override
    public void initialize(MyConstrant constraintAnnotation) {
        System.out.println("my validator init");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        helloService.greeting("tom");
        System.out.println(value);

        return false;
    }
}
