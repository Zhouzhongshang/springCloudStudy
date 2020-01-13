package com.zhou.servicefeign.controller.hutool.clone;

import cn.hutool.core.clone.CloneSupport;
import cn.hutool.core.lang.Console;

import com.zhou.servicefeign.controller.hutool.meiju.SingleInstance;
import lombok.Data;


@Data
public  class Dog extends CloneSupport<Dog> {

    private String name= "wangwang";
    private int age=3;


    public Dog getDog(){
        return new Dog().clone();
    }

    public static void main(String[] args) {
        SingleInstance instance = SingleInstance.INSTANCE;
        SingleInstance instance1 = SingleInstance.INSTANCE;
        Dog dog = new Dog().getDog();
        Console.log(dog);
        System.out.println(instance==instance1);
    }

}
