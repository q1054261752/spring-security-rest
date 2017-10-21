package com.imooc.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zkr on 2017/10/21.
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstrantValidator.class)
public @interface MyConstrant {

    //以下的属性必须写
    String message() default "fail";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
    //以上的属性必须写
}
