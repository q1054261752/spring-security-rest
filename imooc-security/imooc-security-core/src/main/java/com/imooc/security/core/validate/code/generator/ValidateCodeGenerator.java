package com.imooc.security.core.validate.code.generator;

import com.imooc.security.core.validate.code.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by zkr on 2017/11/6.
 */
public interface ValidateCodeGenerator {

     ValidateCode createImageCode(ServletWebRequest request);
}
