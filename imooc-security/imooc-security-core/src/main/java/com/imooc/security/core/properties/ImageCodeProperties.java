package com.imooc.security.core.properties;

/**
 * Created by zkr on 2017/11/4.
 */
public class ImageCodeProperties extends SmsCodeProperties{

    private int width = 67;
    private int height = 23;

    public ImageCodeProperties(){
        setLength(4);   //设置图片验证码长度的默认值是4
    }

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
}
