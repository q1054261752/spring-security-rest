package com.imooc.security.core.validate.code.sms.impl;

import com.imooc.security.core.validate.code.sms.SmsCodeSender;

/**
 * Created by zkr on 2017/11/6.
 */
public class DefaultSmsCodeSender implements SmsCodeSender{
    @Override
    public void send(String mobile,String code) {

        System.out.println("手机号:" + mobile + "," + "验证码:" + code);
    }
}
