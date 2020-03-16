package com.zhou.servicefeign.controller.annotationdemo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: sc-f-chapter1
 * @description: aop实现日志
 * @author: zzs
 * @create: 2020-03-16 15:27
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyLog {
}
