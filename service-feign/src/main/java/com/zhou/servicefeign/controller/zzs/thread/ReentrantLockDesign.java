package com.zhou.servicefeign.controller.zzs.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: sc-f-chapter1
 * @description: 可重入锁模拟抢单
 * @author: zzs
 * @create: 2020-12-06 13:34
 *
 * 1.0，Callable接口，FutureTask可以获取结果，和抛出异常，知道获取结果
 *
 * 生命周期
 * 2.0 join,先让start(),再join是执行完，再让其它的执行
 * 3.0 sleep,自己处于阻塞状态，当醒过来时，进入等待队列，等到拿到cpu就可以执行了。
 * 4.0 yield,让步不是进入阻塞状态，而是进入就绪状态，不需要时间无异常
 * 5.0 中断、以及后台线程的设置
 *
 * ======================================结合线程的生命周期理解=====================================================
 *
 * Obj.wait()与Obj.notify()必须要与synchronized(Obj)一起使用
 * 1.0，wait()使当前线程休眠,让出对象锁，当其它线程调用当前线程notify()时才会进入到就绪状态等待队列。
 * 2.0，notify()是对象锁的唤醒操作。
 * 3.sleep()方法是使当前线程让出cpu,进入到阻塞状态，时间到，唤醒，进入线程进入就绪状态等待队列。
 * 顺序上：先通知、后wait
 **/
public class ReentrantLockDesign {
    //总共100个苹果，A每次拿3个 B每次拿5个 当不够拿时停止，输出最后的苹果数量
    //设计有问题

    static int apple = 100;

    public static void main(String[] args) {

        PickThreeAppleMonkey pickThreeAppleMonkey = new PickThreeAppleMonkey();
        PickFiveAppleMonkey pickFiveAppleMonkey = new PickFiveAppleMonkey();

        new Thread(pickThreeAppleMonkey,"thread1").start();
        new Thread(pickFiveAppleMonkey,"thread2").start();

    }

    static class PickThreeAppleMonkey implements Runnable{

        private ReentrantLock lock = new ReentrantLock();

        @Override
        public void run() {
            // ...
            int count = 0;
            while (true){
                lock.lock();
                try {
                        if (apple < 3){
                            System.out.println(Thread.currentThread().getName() + " " +"A已经拿完了");
                            break;
                        }
                    System.out.println(Thread.currentThread().getName() + "数量剩余："+ (apple = apple - 3) + "次数："+ (++count));
                        Thread.sleep(100);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class PickFiveAppleMonkey implements Runnable{

        private ReentrantLock lock = new ReentrantLock();

        @Override
        public void run() {
            // ...

            int count = 0;
            while (true){
                lock.lock();
                try {
                        if (apple < 5){
                            System.out.println(Thread.currentThread().getName() + " " +"B已经拿完了");
                            break;
                        }
                        System.out.println(Thread.currentThread().getName() + "数量剩余："+ (apple = apple - 5) +"次数："+ (++count));
                        Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

        }
    }
}
