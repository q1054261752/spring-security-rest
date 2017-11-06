package com.imooc.code;

import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.code.generator.ValidateCodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by zkr on 2017/11/6.
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator{
    @Override
    public ValidateCode create(ServletWebRequest request) {
        System.out.println("更高级的图形验证码生成代码");
        return null;
    }
}
