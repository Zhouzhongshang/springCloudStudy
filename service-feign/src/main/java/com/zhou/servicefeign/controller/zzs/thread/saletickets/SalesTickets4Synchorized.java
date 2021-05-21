package com.zhou.servicefeign.controller.zzs.thread.saletickets;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: sc-f-chapter1
 * @description: 10个窗口同步锁销售票1000张
 * @author: zzs
 * @create: 2021-05-20 16:24
 * <p>
 * 10个线程死循环获取锁
 **/
@SuppressWarnings(value = "all")
public class SalesTickets4Synchorized {
    public static void main(String[] args) throws InterruptedException {

        TicketsInventory ticketsInventory = new TicketsInventory();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                //每一个线程都在死循环
                while (true) {
                    //在死循环中不断的获取锁
                    synchronized (ticketsInventory) {
                        try {
                            //为了看清除点，睡眠10进入阻塞状态让出cpu,
                            //   疑惑：但是该线程任然持有锁? 是持有锁的，其他线程拿到CPU之后，应该也扣减不了库存呀，那么为啥其他线程可以扣减库存呢？
                            //   答案：===这个疑惑是，其他线程并没有扣，我们看到的而是该线程执行完synchronized代码块后，释放锁，被其他线程抢到了再扣减的。
                            //但是该线程任然持有锁?,即使时其它线程获取CPu时，发现锁被占用了，仍然扣减不了库存。直到，该线程CPU时间段用完了，释放锁，让出CPU.其他线程才可以操作它

                            //总结：只有某个线程的CPU时间片用完了才会释放锁，和让出CPU的执行权。
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //最后直到该线程扣减库存
                        ticketsInventory.salesTickets();
                    }

                }
            }, "Thread:" + i).start();
        }

        ReentrantLock lock = new ReentrantLock(true);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {

                //每一个线程死循环获取锁
                while (true) {

                    try {
                        lock.lock();
                        ticketsInventory.salesTickets();
                    } finally {
                        //然后释放锁
                        lock.unlock();
                    }

                }
            }, "Thread:" + i).start();
        }
    }
}
