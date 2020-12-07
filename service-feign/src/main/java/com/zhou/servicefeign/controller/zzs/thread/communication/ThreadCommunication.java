package com.zhou.servicefeign.controller.zzs.thread.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: sc-f-chapter1
 * @description: 线程通信
 * @author: zzs
 * @create: 2020-12-07 15:30
 *
 * 实现一个生产者和一个消费者，生产一个商品，满了通知消费者，空通知生产者且是线程安全
 * Lock.lock lock.newCondition队列
 **/
public class ThreadCommunication {

    public static void main(String[] args) {

        //共享资源
         Product product = new Product();
         Lock lock = new ReentrantLock();
         Condition producerCondition = lock.newCondition();
         Condition consumerCondition = lock.newCondition();

        Thread pr = new Thread(new Producer(product,producerCondition,lock,consumerCondition));
        Thread co = new Thread(new Consumer(product,consumerCondition,lock,producerCondition));

        pr.start();
        co.start();

    }



    static class Consumer implements Runnable {
        public Consumer(Product product, Condition condition, Lock lock, Condition producerCondition) {
            this.product = product;
            this.consumerCondition=condition;
            this.lock=lock;
            this.producerCondition=producerCondition;
        }

        private Product product;
        private Condition consumerCondition;
        private Lock lock;
        private Condition producerCondition;

        @Override
        public void run() {

            while (true) {
               // synchronized (product) {
                lock.lock();
                try {
                    System.out.println("消费商品：" + product.toString());
                    producerCondition.signalAll();
                    consumerCondition.await();
                   // product.notifyAll();
//                    try {
//                        consumerCondition.await();
//                     //   product.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
              //  }
            }
        }
    }

    static class Producer implements Runnable {

        public Producer(Product product, Condition condition, Lock lock, Condition consumerCondition) {
            this.product = product;
            this.producerCondition=condition;
            this.lock=lock;
            this.consumerCondition=consumerCondition;
        }

        private Product product;
        private Condition producerCondition;
        private Lock lock;
        private Condition consumerCondition;

        @Override
        public void run() {

            int i =0;
            while (true) {
                lock.lock();
                try {
                   // synchronized (product) {
                        if (i % 2 == 0) {
                            product.name = "馒头";
                            product.color = "白色";
                        } else {
                            product.name = "包子";
                            product.color = "黄色";
                        }
                        System.out.println("生产商品：" + product.toString());

                        i++;
                    //    product.notifyAll();
                         consumerCondition.signalAll();
                         producerCondition.await();
//                        try {
//                     //       product.wait();
//                            producerCondition.await();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                  //  }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }


            }
        }
    }
}