package com.zhou.servicefeign.controller.zzs.enu;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

/**
 * @program: sc-f-chapter1
 * @description: 测试枚举
 * @author: zzs
 * @create: 2020-12-17 10:07
 **/
public class TestEnum {

    private static Compiler OSExecute;

    enum StatusTrafficEnum{
        //枚举所有的颜色
        RED{
            @Override
            void doSomeThing() {

            }
        }
        ,YELLOW {
            @Override
            void doSomeThing() {

            }
        },BLUE {
            @Override
            void doSomeThing() {

            }
        };

        abstract void doSomeThing();

    }

    static class Reflection{
        public static Set<String> analyze(Class<?> enumClass){
            System.out.println(
                    "_____ Analyzing " + enumClass + " _____");
            System.out.println("Interfaces:");
            for(Type t : enumClass.getGenericInterfaces()) {
                System.out.println(t);
            }
            System.out.println(
                    "Base: " + enumClass.getSuperclass());
            System.out.println("Methods: ");
            Set<String> methods = new TreeSet<>();
            for(Method m : enumClass.getMethods()) {
                methods.add(m.getName());
            }
            System.out.println(methods);
            return methods;
        }

    }

    public static void main(String[] args) {
       StatusTrafficEnum signal= StatusTrafficEnum.RED;
        switch (signal){
            case RED:
                System.out.println(signal);
                break;
            case BLUE:
                System.out.println(signal);
                break;
            case YELLOW:
                System.out.println(signal);
                break;
            default:
                System.out.println(signal);
        }

        //探究枚举到底做了啥？
        Set<String> analyze = Reflection.analyze(StatusTrafficEnum.class);
        Set<String> analyze1 = Reflection.analyze(Enum.class);

        Compiler.command(
                "javap -cp build/classes/main StatusTrafficEnum");
    }
}
