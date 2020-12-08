package com.zhou.servicefeign.controller.zzs.single;

/**
 * @program: sc-f-chapter1
 * @description: 单例模式
 * @author: zzs
 * @create: 2020-12-08 10:50
 **/
public class Single03 {
    private static Single03 single03 = null;

    private Single03(){

    }

    public static Single03 getInstance(){
        if (null == single03){
            //如果要线程安全，则在这个步骤需要线程安全的，参考Single01
            single03 = new Single03();
        }
        return single03;
    }
}
