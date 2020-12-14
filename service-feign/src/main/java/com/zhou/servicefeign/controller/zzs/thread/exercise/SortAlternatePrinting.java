package com.zhou.servicefeign.controller.zzs.thread.exercise;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

/**
 * @program: sc-f-chapter1
 * @description:排序和交替打印
 * @author: zzs
 * @create: 2020-12-09 18:24
 **/
public class SortAlternatePrinting {
    public static void main(String[] args) {
        int [] arr = {5,8,10,3,2,11,78,1};


        for (int i = 0; i <arr.length -1 ; i++) {
            //第一遍找到最小的值
            for (int k = i+1; k < arr.length ; k++) {
               if (arr[k] < arr[i]){
                   //
                   int tem = arr[i];
                   arr[i] = arr[k];
                   arr[k] = tem;
               }
            }
        }

        for (int i = 0; i <arr.length ; i++) {
            System.out.println(arr[i]);
        }

        FooBar fooBar = new FooBar();


        //注意使用ReentrantLock：bar不能放在前面运行，因为循环的时候第一次循环不会打印bar,所以
        //i=0时候是失效的一次

        //信号量最简单

        new Thread(()->{
            try {
                fooBar.bar1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                fooBar.foo1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }


    static class FooBar {

        private ReentrantLock lock = new ReentrantLock();

        private Condition fooCondition = lock.newCondition();
        private Condition barCondition = lock.newCondition();

        private int n =100;

        private volatile Boolean flag = true;

        private Semaphore fooSemaphore = new Semaphore(1);
        private Semaphore barSemaphore = new Semaphore(0);


        public void foo1() throws InterruptedException {
            for (int i = 0; i <n ; i++) {
                //获取一个许可
                fooSemaphore.acquire();
                //执行
                System.out.println("foo");
                //释放一个许可
                barSemaphore.release();
            }
        }

        public void bar1() throws InterruptedException {
            for (int i = 0; i <n ; i++) {
                //获取一个许可
                barSemaphore.acquire();
                System.out.println("bar");
                //释放一个许可
                fooSemaphore.release();
            }
        }

        public void foo() {
          for (int i = 0; i < n; i++) {
              lock.lock();

              try {
                  if (flag) {
                      System.out.println("foo");
                      flag=false;
                  }
                  barCondition.signal();
                  fooCondition.await();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              } finally {
                  lock.unlock();
              }
         }
        }

        public void bar() {
            for (int i = 0; i < n; i++) {

                lock.lock();
                try {
                    if (!flag){
                        System.out.println("bar");
                        flag=true;
//                        fooCondition.signal();
//                        barCondition.await();
                        Thread.sleep(100);
                    }
                    fooCondition.signal();
                    barCondition.await();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
          }
        }
    }

