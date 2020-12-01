package com.zhou.servicefeign.controller.wjj;

import com.zhou.servicefeign.dao.wjj.BossPostDao;
import com.zhou.servicefeign.pojo.wjj.entity.BossPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: sc-f-chapter1
 * @description: wjjcontroller
 * @author: zzs
 * @create: 2020-04-05 15:56
 **/
@RestController
@RequestMapping("/wjj")
public class BossPostController {

    @Autowired
    private BossPostDao bossPostDao;

    @GetMapping("insertData")
    public void insertBatch(){
        List<BossPost> bossPosts = BossPost.getBossPosts();
         bossPosts.forEach(i->{
             bossPostDao.insert(i);
         });
    }

    /**
     * 使用原子类完成多线程的交替打印
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

  //  final     AtomicInteger atomicInteger = new AtomicInteger(0);

        Thread a = new Thread(() -> {
            System.out.println("3:"+Thread.currentThread().getName());
        }, "A");

        Thread b = new Thread(() -> {
            try {
                a.join();
                a.start();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2:"+Thread.currentThread().getName());
        }, "B");
        Thread c = new Thread(() -> {
            try {
                b.join();
                b.start();
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1:"+Thread.currentThread().getName());
        }, "C");

        c.start();

    }
}
