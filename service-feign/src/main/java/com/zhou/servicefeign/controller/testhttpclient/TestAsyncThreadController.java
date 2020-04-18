package com.zhou.servicefeign.controller.testhttpclient;

import com.zhou.servicefeign.service.AsyncServiceImpl.AsyncServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: sc-f-chapter1
 * @description: 多线程异步处理触发任务zhix
 * @author: zzs
 * @create: 2020-04-18 13:24
 **/
@RestController
public class TestAsyncThreadController {

    @Autowired
    private AsyncServiceImpl asyncService;


    @RequestMapping("doTask")
    public String doTask() throws InterruptedException {

        /**
         * 异步实现任务处理，然后直接返回前端
         */
            asyncService.exeAsncService1();
            asyncService.exeAsncService2();

        return "success";
    }

}
