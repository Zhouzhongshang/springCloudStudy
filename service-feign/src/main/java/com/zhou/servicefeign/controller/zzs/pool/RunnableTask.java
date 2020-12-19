package com.zhou.servicefeign.controller.zzs.pool;

import com.zhou.servicefeign.controller.zzs.single.ThreadPoolSingle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * @program: sc-f-chapter1
 * @description: 执行RunableTask
 * @author: zzs
 * @create: 2020-12-17 13:59
 **/
public class RunnableTask {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //执行无返回结果的
        for (int i = 0; i <10 ; i++) {
            int j = i;
            executorService.execute(()->{
                   System.out.println("任务开始========="+j);
                   System.out.println("任务结束========="+j);
            });
        }
        //执行有返回值的
        List<Future<Integer>> results = new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
           //提交5个任务
            Future<Integer> submit = executorService.submit(new MyCallAbleTask());
            //
           // Integer integer = submit.get();这个方法是阻塞的，会直接等待返回结果的
            results.add(submit);
        }

        results.forEach(integerFuture -> {
            try {
               System.out.println( integerFuture.get() );
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();

    }

    static class MyCallAbleTask implements Callable{

        @Override
        public Integer call() throws Exception {
            return new Random().nextInt(10);
        }
    }
}
