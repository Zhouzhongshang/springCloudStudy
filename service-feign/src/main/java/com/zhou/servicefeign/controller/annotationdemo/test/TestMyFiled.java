package com.zhou.servicefeign.controller.annotationdemo.test;

import com.zhou.servicefeign.controller.annotationdemo.MyFiled;

import java.lang.reflect.Field;
import java.util.function.Consumer;

/**
 * @program: sc-f-chapter1
 * @description: 测试注解
 * @author: zzs
 * @create: 2020-03-15 20:53
 *
 *   可以做一个修改。添加。删除的监控记录。对相关的对象加上注解。传入old  new  和Class信息
 *   反射创建对象，获取字段上的注解。输出改变的值：可以用map实现输出。
 **/
public class TestMyFiled {

    @MyFiled(description = "用户名",length = 12)
    String username;

    public static void main(String[] args) {

        Consumer consumer =System.out::println;

        /**
         * @Param: [args]
         * @return: void
         * @Author: 86157
         * @Date: 2020/3/15
         * @Implementation:
         * @Description:
         * 通过反射拿到类 ，再拿到类的所有字段，再判断字段上有没有某个注解，如果有则获取注解的值然后再进行处理
         *
         */
        //已知注解用在的类上，然后再处理.
        Class testMyFiledClass=TestMyFiled.class;

        //反射拿到类信息，然后再拿到字段，然后再判断字段上是否有注解，再拿到值
        for (Field filed : testMyFiledClass.getDeclaredFields()){
            if (filed.isAnnotationPresent(MyFiled.class)){
                MyFiled annotation = filed.getAnnotation(MyFiled.class);
              consumer.accept("描述："+annotation.description()+"\n长度："+annotation.length()+"\n字段名为："+filed.getName());
            }
        }
    }
}
