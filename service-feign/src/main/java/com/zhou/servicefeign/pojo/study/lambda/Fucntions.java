package com.zhou.servicefeign.pojo.study.lambda;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author 86157
 * 函数：输入输出功能
 */
public class Fucntions {
    public static void main(String[] args) {
        //6
        Function<Integer,Integer> f1 = integer -> integer*3;
        //36
        Function<Integer,Integer> f2 = integer -> integer*integer;
           Consumer consumer=  System.out::println;
           //打印一次值为2*3）平方
           consumer.accept(f1.andThen(f2).apply(2));
    }
}
