package com.imooc.handler;

import com.imooc.exception.UserNotException;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

/**
 * Created by zkr on 2017/10/21.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> handlerUserNotException(UserNotException e){

        Map<String,Object> map = new HashedMap();
        map.put("id",e.getId());
        map.put("message",e.getMessage());

        return map;
    }

}
