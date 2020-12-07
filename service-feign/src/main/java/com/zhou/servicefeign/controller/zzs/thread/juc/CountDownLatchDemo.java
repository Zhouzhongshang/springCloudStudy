package com.zhou.servicefeign.controller.zzs.thread.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: sc-f-chapter1
 * @description: 计数递减
 * @author: zzs
 * @create: 2020-12-07 22:17
 *
 * ===================当所有的计数为0时，才结束
 **/
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(2);

        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //提交任务
        executorService.submit(()->{
            System.out.println("任务一========");
            countDownLatch.countDown();
        });
        executorService.submit(()->{
            System.out.println("任务二========");
            countDownLatch.countDown();
        });
        countDownLatch.await();
        System.out.println("结束==================");
        //关闭线程池
        executorService.shutdown();
    }
}
