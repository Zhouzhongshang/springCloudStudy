package com.zhou.servicefeign.controller.zzs.single;

import java.util.concurrent.*;

/**
 * @program: sc-f-chapter1
 * @description: 线程池单例模式获取
 * @author: zzs
 * @create: 2020-12-18 19:20
 **/
public class ThreadPoolSingle {

    private static ExecutorService pool;

    public  static ExecutorService getExecutorService(){
        if (pool==null){
            synchronized (ExecutorService.class){
                if (pool==null){
                   pool = new ThreadPoolExecutor(
                            5,
                            200,
                            0L, TimeUnit.MILLISECONDS,
                            new LinkedBlockingQueue<Runnable>(1024),
                            new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
        return pool;
    }
}
