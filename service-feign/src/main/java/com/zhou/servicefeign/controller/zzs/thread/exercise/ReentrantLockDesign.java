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
 * 2.0 join,先start(),再join是执行完，再让其它的执行
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
 *  =======================线程通信notify wait之前必须同步synchorized======================
 *
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
 *        可见性：  原理：volatile: mainApp->cpuCache 读取（直接从主存中读取），当写的时候直接写到主存中和cpuCache中。
 *
 *       有序性：  原理：禁止指令重排：当 123 12有依赖关系会顺序执行，2中依赖了1.
 *                        而3没有依赖关系会将其移动到前面执行。当多线程时就会出现问题。而volatile则禁止重排序。
 *                        ->内存屏障（指令重排序后，禁止把后面的指令放到前面执行）
 *                  性能：volatile 的读性能消耗与普通变量几乎相同，但是写操作稍慢，因为它需要在本地代码中插入许多内存屏障指令来保证处理器不发生乱序执行。
 *                       直接从主存读取、绕过cpu高速缓存。
 *
 *        原子性：  需要借助CAS来保证
 *                   ABA问题：1A->B->A  2A--。 解决方案时间戳或者版本号
 *
 *         synchronized可以保证变量的 【可见性 原子性 有序性】
 *
 * Lock.lock lock.newCondition队列
 *
 * ===============================高级功能=============================================================
 * LOCK:  ReentrantLock
 * ReadWriteLock lock = new ReentrantReadWriteLock()
 * lock.readLock.lock()获取锁、   lock.readLock.unlock()释放锁
 *
 * 可重入锁、本对象的锁在锁住的方法里又进入到该对象的锁了，此时不需要释放第一把锁就可以直接进入。
 * 独占锁(writeLock|reentrantlock/lock/synchronized)、共享锁（readlock）
 * 公平锁、非公平锁
 *
 *
 * BlockingQueue:
 *   ArrayBlockingQueue有界阻塞队列底层是Lock 和Condition实现。put take
 *   LinkedBlockingQueue整数最大值。
 *
 * 线程池：还会有一个线程工厂委托其来创建线程、然后还有一个丢弃策略
 *    ：源码是一个接口。里面方法一个是runable 一个是线程池
 *
 * 练习：
 *   三个线程轮着打印1-5 6-10 11-15。使用Synchronized
 *
 *
 *   1: if（conditon == 1）  wait()  service处理完 notify
 *
 *     视频代码：
 *       1.1、条件不满足：wait让出了cpu时间片和对象锁、进入到等待队列
 *       1.2、当业务执行完成偶：notify通知其他线程进入到就绪状态，业务上主动释放了对象锁。但cpu可能还没有用完。
 *       如果，这个时候啊，该线程又获取到了对象锁，那么进入业务方法会达到条件不满足，进入到1.1的状态。
 *       最后其它线程获取cpu和对象锁。其它线程会重复此操作。
 *
 *      我的逻辑：
 *        条件不满足->通知其他线程让出时间片
 *        业务执行完->通知其他线程让出时间片
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
