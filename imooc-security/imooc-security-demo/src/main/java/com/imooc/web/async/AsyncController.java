package com.imooc.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.metrics.util.SimpleInMemoryRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * Created by zkr on 2017/10/24.
 */
@RestController
public class AsyncController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/custormOrder")
    public String custormOrder() throws InterruptedException {
        logger.info("主线程开始");
        Thread.sleep(1000);
        logger.info("主线程结束");
        return "sccess";
    }
    @RequestMapping("/order")  //使用Runnable处理异步处理Rest服务，这个用Callable
    public Callable<String> order(){
        logger.info("主线程开始");
        Callable<String>  result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("副线程开始");
                logger.info("副线程返回");
                return "success";
            }
        };
        logger.info("主线程结束");
        return result;
    }






}
