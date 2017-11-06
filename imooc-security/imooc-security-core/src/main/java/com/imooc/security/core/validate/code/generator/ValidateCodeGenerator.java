package com.imooc.security.core.validate.code.generator;

import com.imooc.security.core.validate.code.ImageCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by zkr on 2017/11/6.
 */
public interface ValidateCodeGenerator {

     ImageCode createImageCode(ServletWebRequest request);
}
