package com.zhou.servicefeign.controller.zzs.thread;

/**
 * @program: sc-f-chapter1
 * @description: 多线程交替打印
 * @author: zzs
 * @create: 2020-12-01 14:28
 * 关注点在已知打印的数字，只不过是交替打印
 *
 * Obj.wait()与Obj.notify()必须要与synchronized(Obj)一起使用
 * 1.0，wait()使当前线程休眠,让出对象锁，当其它线程调用当前线程notify时才会进入到等待队列。
 * 2.0，notify()是对象锁的唤醒操作。
 * 3.sleep()方法是使当前线程让出cpu,进入到阻塞状态，时间到，唤醒，进入线程进入等待队列。
 * 顺序上：先通知、后wait
 **/
public class PrintNum {

    //共享变量可见性
    static volatile Boolean flagOne = false;
    static volatile Boolean flagTwo = false;


    /**
     * 打印1后通知其他线程打印2
     * @throws InterruptedException
     */
    public void one() throws InterruptedException{
        synchronized (this) {
            boolean flag = true;

            while (flag) {

                for(int i = 1; i <= 99;i += 2){
               //     Thread.sleep(200);
                    System.out.println("A:"+i);

                    if(i==99){
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
     * @throws InterruptedException
     */
    public void two() throws InterruptedException{
        synchronized (this) {
            boolean flag = true;

            while (flag) {

                for(int i = 2; i <= 100;i += 2){
                    System.out.println("B:"+i);

                  //  Thread.sleep(100);
                    if(i==100){
                        flag = false;
                        this.notify();
                        flagTwo=Boolean.TRUE;
                        break;
                    }
                    this.notify();
                    this.wait();
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        //启动两个线程交替打印1-100
        PrintNum sumThread = new PrintNum();


        new Thread(()->{
            try {
                sumThread.one();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

      // TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            try {
                sumThread.two();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        while (true){
            if (flagOne && flagTwo){
                System.out.println("完成");
                break;
            }
        }

    }

}
