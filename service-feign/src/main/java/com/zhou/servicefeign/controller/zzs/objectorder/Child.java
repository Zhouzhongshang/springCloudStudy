package com.zhou.servicefeign.controller.zzs.objectorder;

/**
 * @program: sc-f-chapter1
 * @description: 子对象
 * @author: zzs
 * @create: 2020-12-17 09:23
 **/
public class Child extends Root{

    //1
    static Root root = new Root(1);

    //2
    private Root rootPrivate = new Root(2);

    //3
    public Child(){
        System.out.println("child()");
    }


}
