package com.zhou.servicefeign.controller.zzs.thread.exercise;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: sc-f-chapter1
 * @description: 多线程打印
 * @author: zzs
 * @create: 2020-12-18 21:58
 * 一个线程打印字母一个线程打印数字。
 * 12A34B56C
 *
 * 注意：  conditionSum.signalAll();   的先后顺序。
 *         conditionChar.await();
 **/
public class MultiThreadedPrinting {

    public static void main(String[] args) {

        Printer printer = new Printer();
        ReentrantLock lock = new ReentrantLock();
        Condition conditionSum = lock.newCondition();
        Condition conditionChar = lock.newCondition();

        NumPrint numPrint = new NumPrint(printer,lock,conditionSum,conditionChar);
        CharPrint charPrint = new CharPrint(printer, lock, conditionSum, conditionChar);

        Thread threadSum = new Thread(numPrint);
        Thread threadChar = new Thread(charPrint);

        threadSum.start();
        threadChar.start();


    }

    static class NumPrint implements Runnable{

        private Printer printer;

        private ReentrantLock lock;

        private Condition conditionSum;

        private Condition conditionChar;

        public NumPrint(Printer printer, ReentrantLock lock, Condition conditionSum, Condition conditionChar) {
            this.printer = printer;
            this.lock = lock;
            this.conditionSum = conditionSum;
            this.conditionChar = conditionChar;
        }

        @Override
        public void run() {

            //只打印12 45 78
            for (int i = 1; i <= 52; i++) {
                   lock.lock();
                   try {
                       printer.print(i);
                       //通知另一个打印
                       if (i % 2 == 0){
                           conditionChar.signalAll();
                           conditionSum.await();
                       }
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   } finally {
                       lock.unlock();
                   }
            }

        }
    }

    static class CharPrint implements Runnable{

        private Printer printer;

        private ReentrantLock lock;

        private Condition conditionSum;

        private Condition conditionChar;

        public CharPrint(Printer printer, ReentrantLock lock, Condition conditionSum, Condition conditionChar) {
            this.printer = printer;
            this.lock = lock;
            this.conditionSum = conditionSum;
            this.conditionChar = conditionChar;
        }

        @Override
        public void run() {
            for (char i = 'A'; i <= 'Z' ; i++) {

                lock.lock();
                try {
                    printer.print(i);
                  //  TimeUnit.MILLISECONDS.sleep(100);
                    //打印完之后通知数字线程打印
                    conditionSum.signalAll();
                    try {
                        conditionChar.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {

                    lock.unlock();
                }
            }
        }
    }

    static class Printer{
        /**
         * 打印数字
         * @param i
         */
        public void print(int i){
            System.out.println(i);
        }

        /**
         * 打印字母
         * @param j
         */
        public void print(char j){
            System.out.println(j);
        }

    }

}
