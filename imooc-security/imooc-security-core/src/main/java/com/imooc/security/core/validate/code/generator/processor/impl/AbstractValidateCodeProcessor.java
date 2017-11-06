package com.imooc.security.core.validate.code.generator.processor.impl;

import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.code.generator.ValidateCodeGenerator;
import com.imooc.security.core.validate.code.generator.processor.ValidateCodeProcessor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.Map;

/**
 * Created by zkr on 2017/11/6.
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {
    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    /**
     * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
     */
    @Autowired
    private Map<String,ValidateCodeGenerator> validateCodeGenerators;
    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        save(request , validateCode);
        send(request , validateCode);
    }
    /**
     * 生成校验码
     * @param request
     * @return
     */
    protected  C generate(ServletWebRequest request){
        String type = getProcessorType(request);
        ValidateCodeGenerator validateCodeGenerator=
                validateCodeGenerators.get(type + "CodeGenerator");
        return (C) validateCodeGenerator.create(request);
    }
    /**
     * 保存校验码
     * @param request
     * @param validateCode
     */
    private void save(ServletWebRequest request,C validateCode){
        sessionStrategy.setAttribute(request,SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(),validateCode);
    }
    /**
     * 发送校验码、由子类实现
     * @param request
     * @param validateCode
     */
    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;


    /**
     * 根据请求的url获取校验码的类型
     * @param request
     * @return
     */
    private  String getProcessorType(ServletWebRequest request){
        return StringUtils.substringAfter(request.getRequest().getRequestURI(),"/code/");
    }


}
