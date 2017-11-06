package com.imooc.security.core.properties;

/**
 * Created by zkr on 2017/11/4.
 */
public class SmsCodeProperties{

    private int length = 6;
    private int exprireIn = 60;

    private String url;

    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public int getExprireIn() {
        return exprireIn;
    }
    public void setExprireIn(int exprireIn) {
        this.exprireIn = exprireIn;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
