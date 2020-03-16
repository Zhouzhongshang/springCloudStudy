package com.zhou.servicefeign.controller.annotationdemo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: sc-f-chapter1
 * @description: 登录判断自定义注解
 * @author: zzs
 * @create: 2020-03-16 11:58
 **/
@Target(ElementType.METHOD)  //作用于方法字段还是类
@Retention(RetentionPolicy.RUNTIME)//运行时生效
public @interface LoginRequired {
}
