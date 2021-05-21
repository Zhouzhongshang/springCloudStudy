package com.zhou.servicefeign.controller.zzs.thread.juc;

import java.util.concurrent.Semaphore;

/**
 * @program: sc-f-chapter1
 * @description: 许可证
 * @author: zzs
 * @create: 2020-12-07 22:48
 *
 *  CountDownLatch和CyclicBarrier的计数器递减的，而Semaphore的计数器是递增
 *
 *  Semaphore：信号量的含义。常用方法如下：指定信号量的个数
 *      release()：释放许可证，将其返回到信号量，可用许可证的数量增加一个
 *      acquire(int n)：从该信号量获取给定数量的许可证，数量不足就阻塞等待
 *
 **/
@
SuppressWarnings("all")
public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {

//        Semaphore semaphore = new Semaphore(0);
//
//        //创建线程池
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        //提交任务
//        executorService.submit(()->{
//            System.out.println("任务一=====1===");
//            System.out.println("任务一=====2===");
//            System.out.println("任务一=====3===");
//            semaphore.release();
//        });
//        executorService.submit(()->{
//            System.out.println("任务二====1====");
//            System.out.println("任务二====2====");
//            System.out.println("任务二=====3===");
//            semaphore.release();
//        });
//        semaphore.acquire(2);
//        System.out.println("结束==================");
//        //关闭线程池
//        executorService.shutdown();


        //共享资源
        Semaphore semaphore = new Semaphore(5);

        for (int i = 0 ; i < 8 ;i++){
            new Thread(()->{
                //获取一个许可，同时模拟工作100，最后释放
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"占用一台机器");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaphore.release();//可能一释放就被别人抢走了，都还没来得及打印下面的
                System.out.println(Thread.currentThread().getName()+"工作完成了");
            },"ThreadName:"+Thread.currentThread().getName()+":"+i).start();
        }
    }

}
