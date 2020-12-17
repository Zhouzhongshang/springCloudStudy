package com.zhou.servicefeign;


import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: sc-f-chapter1
 * @description: 滴滴面试：线程安全单例模式
 * @author: zzs
 * @create: 2020-12-16 20:28
 **/
public class Single {

    private static ReentrantLock lock = new ReentrantLock();

    private static Single single;

    private Single(){

    }

    public static Single getInstance(){

        if (null == single){
            //线程安全地创建对象
            lock.lock();
            try {
               if (null == single){
                   single = new Single();
               }
            }finally {
                lock.unlock();
            }
//            synchronized (Single.class){
//                if (null == single){
//                    single = new Single();
//                }
//            }
        }
        return single;

    }

}
