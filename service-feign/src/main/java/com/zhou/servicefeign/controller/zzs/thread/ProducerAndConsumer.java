package com.zhou.servicefeign.controller.zzs.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @program: sc-f-chapter1
 * @description: 生产者消费者
 * @author: zzs
 * @create: 2020-11-30 21:37
 **/
public class ProducerAndConsumer {
    public static void main(String[] args) {

        LinkedBlockingQueue<Integer> tasks = new LinkedBlockingQueue<>(5);

        //  ArrayBlockingQueue<Integer> tasks = new ArrayBlockingQueue<>(10);

        Thread pr = new Thread(new Producer(tasks));
        Thread co = new Thread(new Consumer(tasks));

        ArrayList<Object> objects = new ArrayList<>();
        Map map= new HashMap<>();

        pr.start();
        co.start();

    }

    /**
     * 模拟生产者投放消息
     */
    static class Producer implements Runnable{

        private BlockingQueue<Integer> blockingQueue;

        public Producer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        int task = 0;
        @Override
        public void run() {
           while (true){
               try {
                   blockingQueue.put(task);
                   Thread.sleep(1000);
                   System.out.println("正在生产："+task);
                   ++task;
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        }
    }

    /**
     * 模拟消费者消费消息
     */
    static class Consumer implements Runnable{

        private BlockingQueue BlockingQueue;

        public Consumer(BlockingQueue blockingDeque) {
            this.BlockingQueue = blockingDeque;
        }

        @Override
        public void run() {
           while (true){
               try {
                   Integer take = (Integer) BlockingQueue.take();
                   Thread.sleep(500);
                   System.out.println("正在消费："+take);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        }
    }
}
