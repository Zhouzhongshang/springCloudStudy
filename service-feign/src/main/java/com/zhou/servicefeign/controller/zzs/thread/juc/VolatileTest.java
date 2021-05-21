package com.zhou.servicefeign.controller.zzs.thread.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: sc-f-chapter1
 * @description: volatile
 * @author: zzs
 * @create: 2020-12-17 12:26
 *
 * volatile+cas实现线程同步
 **/
public class VolatileTest {

    volatile  static AtomicInteger  atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        //作用是等待10个线程都执行完成之后再获取结果。
//        CountDownLatch countDownLatch = new CountDownLatch(10);
//        for (int i = 0; i < 10; i++) {
//            new Thread(()->{
//
//                for (int j = 0; j < 10000 ; j++) {
//                     atomicInteger.incrementAndGet();
//                }
//                countDownLatch.countDown();
//
//            }).start();
//        }
//
//        countDownLatch.await();
//
//        System.out.println(atomicInteger.get());


        for (int i = 0; i < 10; i++) {
            new Thread(()->{

                for (int j = 0; j < 10000 ; j++) {
                    atomicInteger.incrementAndGet();
                }
            }).start();
        }

        Thread.sleep(100);

        System.out.println(atomicInteger.get());
    }
}
