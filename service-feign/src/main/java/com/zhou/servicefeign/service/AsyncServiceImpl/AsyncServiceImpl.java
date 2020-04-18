package com.zhou.servicefeign.service.AsyncServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @program: sc-f-chapter1
 * @description: 异步业务处理
 * @author: zzs
 * @create: 2020-04-18 13:20
 **/
@Service
@Slf4j
public class AsyncServiceImpl {

    /**
     * 执行异步业务1
     * @param
     */
    @Async
    public void exeAsncService1() throws InterruptedException {
        for (int i = 0 ; i< 20 ;i++){
            Thread.sleep(200);
            log.info("线程[1]值:"+i);
        }

    }

    @Async
    public void exeAsncService2() throws InterruptedException {
        for (int i = 0 ; i< 20 ;i++){
            Thread.sleep(200);
            log.info("线程[2]值:"+i);
        }
    }

}
