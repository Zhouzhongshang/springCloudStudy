package com.zhou.servicefeign.controller.annotationdemo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: sc-f-chapter1
 * @description: 注解
 * @author: zzs
 * @create: 2020-03-15 20:47
 **/
@Target(ElementType.FIELD)  //  注解用于字段上
@Retention(RetentionPolicy.RUNTIME)  // 保留到运行时，可通过注解获取
public @interface MyFiled {
    /**
     * @Description: 描述字段的长度和作用
     * @Param: 
     * @return: 
     * @Author: 86157
     * @Date: 2020/3/15
     * @Implementation: 
     *
     */
    String description();
    int length();
}
