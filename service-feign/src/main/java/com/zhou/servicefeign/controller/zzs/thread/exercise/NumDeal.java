package com.zhou.servicefeign.controller.zzs.thread.exercise;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: sc-f-chapter1
 * @description: 多线程对数字处理-（4个线程2个线程对数字加一2个线程对数字减少1）
 * @author: zzs
 * @create: 2020-12-19 00:07
 **/
public class NumDeal {

    volatile  static AtomicInteger  atomicInteger = new AtomicInteger(10);

    public static void main(String[] args) throws InterruptedException {


        /**
         * 保证数据的原子性CAS  有序性  可见性
         */

        for (int i = 0; i <2 ; i++) {

            new Thread(()->{
                for (int j = 0; j < 100 ; j++) {
                    atomicInteger.incrementAndGet();
                }
            },"Thread"+i).start();
          //  new Thread(new Incre(atomicInteger)).start();
        }

        for (int i = 2; i <4 ; i++) {
            new Thread(()->{
                for (int j = 0; j <100 ; j++) {
                    atomicInteger.decrementAndGet();
                }
            },"Thread:"+i).start();
           // new Thread(new Decre(atomicInteger)).start();
        }

        Thread.sleep(3);

        System.out.println(atomicInteger.get());


    }

    static class Incre implements Runnable{

        private  AtomicInteger atomicInteger;

        public Incre(AtomicInteger atomicInteger) {
            this.atomicInteger = atomicInteger;
        }

        @SneakyThrows
        @Override
        public void run() {

            for (int i = 0; i <10 ; i++) {
                int j = atomicInteger.incrementAndGet();
                System.out.println(Thread.currentThread().getName()+":"+j);
                //Thread.sleep(100);
            }

        }
    }


    static class Decre implements Runnable{

        private volatile AtomicInteger atomicInteger;

        public Decre(AtomicInteger atomicInteger) {
            this.atomicInteger = atomicInteger;
        }

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i <10 ; i++) {
                int j = atomicInteger.decrementAndGet();
                System.out.println(Thread.currentThread().getName()+":"+j);
             //   Thread.sleep(100);
            }

        }
    }
}
