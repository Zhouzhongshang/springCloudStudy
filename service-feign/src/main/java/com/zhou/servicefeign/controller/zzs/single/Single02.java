package com.zhou.servicefeign.controller.zzs.single;

/**
 * @program: sc-f-chapter1
 * @description: 单例模式
 * @author: zzs
 * @create: 2020-12-08 10:48
 **/
public class Single02 {

    //静态方法
    private static Single02 single02 = new Single02();

    //构造方法私有化
    private Single02(){

    }

    //静态方法
    public static Single02 getInstance(){
        return single02;
    }
}
