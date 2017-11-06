package com.imooc.security.core.validate.code.sms;

/**
 * Created by zkr on 2017/11/6.
 */
public interface SmsCodeSender {

    /**
     * 发送短信验证码
     */
    public void send(String mobile,String code);
}
