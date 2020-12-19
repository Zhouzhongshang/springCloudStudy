package com.zhou.servicefeign.controller.zzs.objectorder;

/**
 * @program: sc-f-chapter1
 * @description: 对象初始化类
 * @author: zzs
 * @create: 2020-12-17 09:21
 **/
public class ObjectInitializationAnalysis {


    /**
     * 父类静态字段->父类非                      ->父类无参构造
     *                    ->子类静态字段->子类非                ->子类构造方法
     * @param args
     */
    public static void main(String[] args) {

        Child child = new Child();


        Root root = new Root(3);

        Root root1 = new Root();

    }

}
