package com.zhou.servicefeign.controller.zzs.objectorder;

/**
 * @program: sc-f-chapter1
 * @description: root根对象
 * @author: zzs
 * @create: 2020-12-17 09:22
 **/
public class Root {

   public int anInt;

    public Root(int anInt) {
        System.out.println("Root():"+anInt);
        this.anInt = anInt;
    }

    public Root() {
        System.out.println("Root()");
    }
}
