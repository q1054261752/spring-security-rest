package com.imooc.exception;

/**
 * Created by zkr on 2017/10/21.
 */
public class UserNotException extends RuntimeException{

    private String id;

    public UserNotException(String id) {

        super("user not find");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
