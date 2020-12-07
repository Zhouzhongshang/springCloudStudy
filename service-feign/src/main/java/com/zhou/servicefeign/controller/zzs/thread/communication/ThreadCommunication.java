package com.zhou.servicefeign.controller.zzs.thread.communication;

/**
 * @program: sc-f-chapter1
 * @description: 线程通信
 * @author: zzs
 * @create: 2020-12-07 15:30
 *
 * 实现一个生产者和一个消费者，生产一个商品，满了通知消费者，空通知生产者且是线程安全
 **/
public class ThreadCommunication {
    public static void main(String[] args) {

        Product product = new Product();

        Thread pr = new Thread(new Producer(product));
        Thread co = new Thread(new Consumer(product));

        pr.start();
        co.start();

    }

    static class Consumer implements Runnable {
        public Consumer(Product product) {
            this.product = product;
        }

        private Product product;

        @Override
        public void run() {

            while (true) {
                synchronized (product) {
                    System.out.println("消费商品：" + product.toString());
                    product.notifyAll();
                    try {
                        product.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    static class Producer implements Runnable {

        public Producer(Product product) {
            this.product = product;
        }

        private Product product;

        @Override
        public void run() {

            //生产100个
          //  for (int i = 0; i < 100; i++) {
            int i =0;
            while (true) {
                synchronized (product) {
                    if (i % 2 == 0) {
                        product.name = "馒头";
                        product.color = "白色";
                    } else {
                        product.name = "包子";
                        product.color = "黄色";
                    }
                    System.out.println("生产商品：" + product.toString());

                    i++;
                    product.notifyAll();
                    try {
                        product.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // }
            }
        }
    }
}