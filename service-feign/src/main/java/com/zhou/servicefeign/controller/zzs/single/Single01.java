package com.zhou.servicefeign.controller.zzs.single;

/**
 * @program: sc-f-chapter1
 * @description: 单例模式
 * @author: zzs
 * @create: 2020-12-08 10:38
 **/
public class Single01 {

    //静态的字段
    private static Single01 single01;

    //禁止外部构造方法
    private Single01(){

    }

    //对外提供的方法
    public static Single01 getInstance(){
        //1.
        if (null == single01 ){
            //需要构造它而且是线程安全的
            synchronized (Single01.class) {
                //当AB线程都执行到1时，如果不判断则都会构造，就线程不安全了
                if (null == single01){
                    single01 = new Single01();
                }
            }
        }
        return single01;
    }

}
