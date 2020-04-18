package com.zhou.servicefeign.controller.testhttpclient;

import cn.hutool.core.date.DateUtil;
import lombok.SneakyThrows;

/**
 * @program: sc-f-chapter1
 * @description: 多线程测试
 * @author: zzs
 * @create: 2020-04-18 12:26
 **/
public class TestMultithreading implements Runnable{
    /**
     * 模拟操作
     */
    @SneakyThrows
    @Override
    public void run() {
        for (int i =0 ;i<200;i++){
            Thread.sleep(300);
            System.out.println("线程："+i);
        }
    }
}
