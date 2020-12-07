package com.zhou.servicefeign.controller.zzs.thread.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @program: sc-f-chapter1
 * @description: 许可证
 * @author: zzs
 * @create: 2020-12-07 22:48
 *
 *
 **/
public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {

        Semaphore semaphore = new Semaphore(0);

        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //提交任务
        executorService.submit(()->{
            System.out.println("任务一=====1===");
            System.out.println("任务一=====2===");
            System.out.println("任务一=====3===");
            semaphore.release();
        });
        executorService.submit(()->{
            System.out.println("任务二====1====");
            System.out.println("任务er====2====");
            System.out.println("任务er=====3===");
            semaphore.release();
        });
        semaphore.acquire(2);
        System.out.println("结束==================");
        //关闭线程池
        executorService.shutdown();
    }

}
