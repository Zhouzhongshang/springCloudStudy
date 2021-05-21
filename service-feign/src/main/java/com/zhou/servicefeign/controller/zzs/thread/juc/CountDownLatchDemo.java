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
 * CountDownLatch是程序递减计数
 *  CountDown是计数递减的意思，Latch是门闩的意思。内部维持一个递减的计数器。可以理解为初始有n个Latch，等Latch数量递减到0的时候，就结束阻塞执行后续操作。
 * countDown( )：减少Latch的计数，如果计数达到零，释放所有等待的线程。
 * await()：导致当前线程等待，直到到Latch计数到零，或者被interrupt。
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
