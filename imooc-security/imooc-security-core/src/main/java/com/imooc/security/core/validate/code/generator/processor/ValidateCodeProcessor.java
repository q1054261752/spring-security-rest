package com.imooc.security.core.validate.code.generator.processor;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码处理器，封装不同验证码的处理逻辑
 *
 * Created by zkr on 2017/11/6.
 */
public interface ValidateCodeProcessor {

    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建校验码
     * @param request
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;

}
