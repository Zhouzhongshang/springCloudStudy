package com.zhou.servicefeign.controller.zzs.thread.saletickets;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: sc-f-chapter1
 * @description: 100张票被4个窗口售卖求每个窗口卖出多少张票
 * @author: zzs
 * @create: 2020-12-07 22:57
 *
 * Lock
 * 思考用：volatile+原子类来实现扣减逻辑
 **/
public class TestSaleTickets {

    public static int tickets = 1000;

    public static  ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {

        for (int i = 0; i <100 ; i++) {
            new Thread(()->{
                //获取锁
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantLock.lock();
                try {
                    //1这里思考需要放在锁外面吗？答案不能，因为，当4个锁同时在此处判断时发现>0。
                    //2当其中一个线程获取锁后，对修改了为0。
                    //3切换其它线程开始扣减逻辑，这里就不会执行校验逻辑了，导致扣减为负数。
                    if (tickets<=0){
                        break;
                    }
                    tickets--;
                    System.out.println(Thread.currentThread().getName() + ":" + tickets);
                } finally {
                    reentrantLock.unlock();
                }
            }
            } , "Thread Name is "+i).start();
        }

    }
}
