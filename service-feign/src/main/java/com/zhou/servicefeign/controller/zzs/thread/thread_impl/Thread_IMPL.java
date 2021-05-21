package com.zhou.servicefeign.controller.zzs.thread.thread_impl;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *
 * 进程：进程是指一种正在运行的程序，有自己的地址空间
 * 线程：是进程内部的最小执行单元。
 *
 * @program: sc-f-chapter1
 * @description: 线程的实现方式
 *    1,继承线程
 *    2，s实现runabale接口
 *    3,实现callable接口
 * @author: zzs
 * @create: 2021-05-20 11:46
 **/
public class Thread_IMPL {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //1.1
//        Thread thread1 = new Thread(new TuristRunable());
//        thread1.start();
//        Thread thread = new Thread(new TuristThrrad());
//        thread.start();
//
//
//        while (true){
//            System.out.println("兔子领先了*****");
//            Thread.sleep(1);
//        }


        //1.2callable接口
//        TuristCallable turistCallable = new TuristCallable();
//        FutureTask<Integer> integerFutureTask = new FutureTask<Integer>(turistCallable);
//        Thread thread = new Thread(integerFutureTask);
//        thread.start();
//        System.out.println(integerFutureTask.isDone());
//        Integer integer = integerFutureTask.get();
//        System.out.println(integer);
//        System.out.println(integerFutureTask.isDone());


        //2.1join
        //        //线程控制
////        thread.yield();
////        thread.join();
////        thread.sleep(1);
////        thread.stop();
////        thread.setDaemon();

        //1
//        Thread thread1 = new Thread(new TuristRunable());
//        int i =0;
//        while (i<=200){
//            i++;
//            System.out.println("兔子领先了*****");
//            if (i==20){
//                //让乌龟领先,需要等待乌龟执行完，才会重新进入执行
//                thread1.start();
//                thread1.join();
//            }
//        }

        //2.2sleep 进入阻塞 时间到，重新被获取CPU，执行代码
//        Thread thread1 = new Thread(new TuristRunable());
//        thread1.start();
//
//        while (true){
//            System.out.println("兔子领先了*****");
//            Thread.sleep(100);
//        }

//
//        Thread thread1 = new Thread(new TuristRunable());
//        thread1.start();
//
//        int i = 0;
//        while (i<100){
//            i++;
//            System.out.println("兔子领先了*****"+i);
//            Thread.yield();
//        }


        Thread thread1 = new Thread(new TuristRunable());
        thread1.setDaemon(true);
        thread1.start();

        int i = 0;
        while (i<100){
            i++;
            System.out.println("兔子领先了*****"+i);
        }


    }
}

class TuristThrrad extends Thread{
    @SneakyThrows
    @Override
    public void run() {
        while (true){
            System.out.println("乌龟领先了Thread*****");
            Thread.sleep(1);
        }
    }
}

class TuristRunable implements Runnable{

    @SneakyThrows
    @Override
    public void run() {
        int i = 0;
        while (i<100){
            i++;
            System.out.println("乌龟领先了Runable*****");
//            Thread.sleep(100);
//            Thread.yield();
        }
    }
}

class TuristCallable implements Callable{

    @Override
    public Object call() throws Exception {
        Thread.sleep(5000);
        return new Random().nextInt(10);
    }
}
