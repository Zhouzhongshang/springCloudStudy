package com.zhou.servicefeign.controller.zzs.thread.threadcommunication;

import lombok.SneakyThrows;

/**
 * @program: sc-f-chapter1
 * @description: 多线程交替打印
 * @author: zzs
 * @create: 2020-12-01 14:28
 * 关注点在已知打印的数字，只不过是交替打印
 * <p>
 * Obj.wait()与Obj.notify()必须要与synchronized(Obj)一起使用
 * 1.0，wait()使当前线程休眠,让出对象锁，当其它线程调用当前线程notify时才会进入到等待队列。
 * 2.0，notify()是对象锁的唤醒操作。
 * 3.sleep()方法是使当前线程让出cpu,进入到阻塞状态，时间到，唤醒，进入线程进入等待队列。
 * 顺序上：先通知notify让其他线程进入就绪状态、后wait让出cpu,同时让出对象锁。
 **/
public class ThreadCommunicationSynchronizedPrintNam {

    //共享变量可见性
    static volatile Boolean flagOne = false;
    static volatile Boolean flagTwo = false;


    /**
     * 打印1后通知其他线程打印2
     *
     * @throws InterruptedException
     */
    public void one() throws InterruptedException {
        synchronized (this) {
            boolean flag = true;

            while (flag) {

                for (int i = 1; i <= 99; i += 2) {
                    System.out.println("A:" + i);

                    if (i == 99) {
                        flag = false;
                        this.notify();
                        flagOne = Boolean.TRUE;
                        break;
                    }
                    this.notify();
                    this.wait();
                }
            }
        }
    }

    /**
     * 打印2后通知其他线程打印1
     *
     * @throws InterruptedException
     */
    public void two() throws InterruptedException {
        synchronized (this) {
            boolean flag = true;

            while (flag) {

                for (int i = 2; i <= 100; i += 2) {
                    System.out.println("B:" + i);

                    if (i == 100) {
                        flag = false;
                        this.notify();
                        flagTwo = Boolean.TRUE;
                        break;
                    }
                    this.notify();
                    this.wait();
                }
            }
        }
    }


    //共享资源100
    public static int source = 100;

    final static Object obj = new Object();

    //3
    static class Get3Thread implements Runnable {

        private int sum = 3;

        @SneakyThrows
        @Override
        public void run() {
            int count = 0;
            while (true) {
                synchronized (obj) {


                    if (source < sum) {
                        System.out.println(Thread.currentThread().getName() + "已经拿完了：" + source);
                        break;
                    }
                    ThreadCommunicationSynchronizedPrintNam.decre(sum);
                    ++count;
                    System.out.println(Thread.currentThread().getName() + "次数：" + count);

                    obj.notify();
                    obj.wait();

                }
            }
        }
    }

    //5
    static class Get5Thread implements Runnable {

        private int sum = 5;

        @SneakyThrows
        @Override
        public void run() {
            int count = 0;
            while (true) {

                synchronized (obj) {
                    if (source < sum) {
                        System.out.println(Thread.currentThread().getName() + "已经拿完了：" + source);
                        break;
                    }
                    ThreadCommunicationSynchronizedPrintNam.decre(sum);
                    ++count;
                    System.out.println(Thread.currentThread().getName() + "次数：" + count);
                    Thread.sleep(100);

                    obj.notify();
                    obj.wait();

                }
            }
        }
    }

    //扣减逻辑
    public static synchronized void decre(int sum) {
        source -= sum;
        System.out.println(Thread.currentThread().getName() + "拿完剩下资源为:" + source);
    }


    public static void main(String[] args) {

        //1.共享资源为100。
        //2.一个取3 一个取5。打印次数。
        //3.方法加锁，对象加锁。

        //如果一个只能拿一次交替去拿呢？

        new Thread(new Get3Thread(), "Thread1").start();
        new Thread(new Get5Thread(), "Thread2").start();


        //启动两个线程交替打印1-100
//        PrintNum sumThread = new PrintNum();
//
//        new Thread(()->{
//            try {
//                sumThread.one();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//      // TimeUnit.SECONDS.sleep(1);
//
//        new Thread(()->{
//            try {
//                sumThread.two();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//        while (true){
//            if (flagOne && flagTwo){
//                System.out.println("完成");
//                break;
//            }
//        }

    }

}
