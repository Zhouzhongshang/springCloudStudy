package com.zhou.servicefeign.controller.zzs.thread.exercise;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Condition;
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
 * synchronized[方法，对象->同步监视器]
 *
 * ======================================结合线程的生命周期理解=====================================================
 *
 * Obj.wait()与Obj.notify()必须要与synchronized(Obj)一起使用
 * 1.0，wait()使当前线程休眠,让出对象锁，当其它线程调用当前线程notify()时才会进入到就绪状态等待队列。
 * 2.0，notify()是对象锁的唤醒操作。
 * 3.sleep()方法是使当前线程让出cpu,进入到阻塞状态，时间到，唤醒，进入线程进入就绪状态等待队列。
 * 顺序上：先通知、后wait
 *
 *
 * ================================AQS==========================================
 * Lock 【可见性 原子性 有序性】
 * ReentrantLock
 * ReadWriteLock read共享大家可以一起读、write必须是一个事务
 * volatile保证多线程之间的变量共享 【可见性 有序性】+ CAS【ABA问题：递增版本解决】 结合ActomicInteger
 *                                                                                  就可以保证线程安全了
 *         读操作很快 写操作稍慢会插入很多内存屏障来禁止重排序
 *         synchronized可以保证变量的 【可见性 原子性 有序性】
 *
 * Lock.lock lock.newCondition队列
 *
 **/
public class ReentrantLockDesign {

    /**
     * 1.不保证顺序执行  需要放大到1000 多执行几次
     * 2.保证顺序执行   需要使用producerCondition consumerCondition 来调用signal await  同notify和wait方法
     * 3.可以考虑优化
     */

    static int apple = 100;

    //特别注意共享资源和锁都是同一个，否则就会失效。
    static  ReentrantLock reentrantLock = new ReentrantLock();

    static Condition aCondition = reentrantLock.newCondition();
    static Condition bCondition = reentrantLock.newCondition();


    public static void main(String[] args) {


        PickThreeAppleMonkey pickThreeAppleMonkey = new PickThreeAppleMonkey(reentrantLock,aCondition,bCondition);
        PickFiveAppleMonkey pickFiveAppleMonkey = new PickFiveAppleMonkey(reentrantLock,aCondition,bCondition);

        new Thread(pickThreeAppleMonkey,"thread1").start();
        new Thread(pickFiveAppleMonkey,"thread2").start();

    }

    static class PickThreeAppleMonkey implements Runnable{

        private ReentrantLock lock;
        private Condition aCondition;

        private Condition bCondition;


        public PickThreeAppleMonkey(ReentrantLock lock) {
            this.lock = lock;
        }


        public PickThreeAppleMonkey(ReentrantLock lock, Condition aCondition, Condition bCondition) {
            this.lock = lock;
            this.aCondition = aCondition;
            this.bCondition = bCondition;
        }

        @SneakyThrows
        @Override
        public void run() {
            // ...
            int count = 0;
            while (true){
                lock.lock();
                try {
                    if (apple < 3){
                            System.out.println(Thread.currentThread().getName() + " " +"A已经拿完了");
                        aCondition.signal();
                        bCondition.await();
                            break;
                    }
                    System.out.println(Thread.currentThread().getName() + "数量剩余："+ (apple = apple - 3) + "次数："+ (++count));

                    bCondition.signal();
                    aCondition.await();

                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class PickFiveAppleMonkey implements Runnable{

        private ReentrantLock lock ;

        private Condition aCondition;

        private Condition bCondition;

        public PickFiveAppleMonkey(ReentrantLock lock) {
            this.lock = lock;
        }

        public PickFiveAppleMonkey(ReentrantLock lock, Condition aCondition, Condition bCondition) {
            this.lock = lock;
            this.aCondition = aCondition;
            this.bCondition = bCondition;
        }

        @Override
        public void run() {
            // ...

            int count = 0;
            while (true){
                lock.lock();
                try {
                        if (apple < 5){
                            System.out.println(Thread.currentThread().getName() + " " +"B已经拿完了");
                            aCondition.signal();
                            bCondition.await();
                            break;
                        }
                        System.out.println(Thread.currentThread().getName() + "数量剩余："+ (apple = apple - 5) +"次数："+ (++count));

                        aCondition.signal();
                        bCondition.await();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

        }
    }
}
