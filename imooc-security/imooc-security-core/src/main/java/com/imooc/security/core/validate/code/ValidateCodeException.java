package com.imooc.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by zkr on 2017/11/2.
 */
public class ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = 8848687408293611636L;

    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
