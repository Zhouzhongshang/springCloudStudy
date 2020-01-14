package com.zhou.servicefeign.pojo.study.lambda;

import java.util.function.Consumer;

/**
 * @author 86157
 * 消费：输出
 */
public class Consumers {
    public static void main(String[] args) {

        Consumer c = System.out::println;
        c.accept("ni");
        c.accept("h");

        c.andThen(c).andThen(c).andThen(c).accept("hallowed");
    }
}
