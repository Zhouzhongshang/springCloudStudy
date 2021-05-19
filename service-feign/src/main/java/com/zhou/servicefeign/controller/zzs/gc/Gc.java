package com.zhou.servicefeign.controller.zzs.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: sc-f-chapter1
 * @description: 模拟OOM
 * @author: zzs
 * @create: 2020-12-19 23:01
 **/
public class Gc {

    Byte [] arr = new Byte[1024*25];

    public static void main(String[] args) throws InterruptedException {

        List<Gc> list = new ArrayList<>();
        while (true){
           Thread.sleep(1);
            list.add(new Gc());
        }
    }
}
