package com.zhou.servicefeign.controller.zzs.thread.juc;

import java.util.concurrent.*;

/**
 * @program: sc-f-chapter1
 * @description: 回环栅栏
 * @author: zzs
 * @create: 2020-12-07 22:22
 *
 * 可以重置的countdownlatch
 **/
public class CyclicBarrierDemo {

    public static void main(String[] args) {
      //  CountDownLatch countDownLatch = new CountDownLatch(2);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //提交任务
        executorService.submit(()->{
            System.out.println("任务一====1====");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("任务一====2====");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("任务一====3====");
            //cyclicBarrier.countDown();
        });

        executorService.submit(()->{
            System.out.println("任务二====1====");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("任务二====2====");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("任务二====3====");
            //cyclicBarrier.countDown();
        });

        //关闭线程池
        executorService.shutdown();
    }

}
