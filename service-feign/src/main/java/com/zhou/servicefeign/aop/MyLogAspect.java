package com.zhou.servicefeign.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @program: sc-f-chapter1
 * @description: 定义一个切面类
 * @author: zzs
 * @create: 2020-03-16 15:28
 **/
@Aspect // 1.表明这是一个切面类
@Component
public class MyLogAspect {
    
    /**
     * @Description: 总结：一个切面类：
     * 对应多个切入点【不同的位置类，比如类，注解类等】：
     * 再对不同的切入点进行相关的方法处理
     * @Param:
     * @return:
     * @Author: 86157
     * @Date: 2020/3/16
     * @Implementation: 
     *
     */

    // 2. PointCut表示这是一个切点，@annotation表示这个切点切到一个注解上，后面带该注解的全类名
    // 切面最主要的就是切点，所有的故事都围绕切点发生
    // logPointCut()代表切点名称
    @Pointcut("@annotation(com.zhou.servicefeign.controller.annotationdemo.MyLog)")
    public void logPointCut(){};

    // 3. 环绕通知
    @Around("logPointCut()")
    public void logAround(ProceedingJoinPoint joinPoint){
        // 获取方法名称
        String methodName = joinPoint.getSignature().getName();
        // 获取入参
        Object[] param = joinPoint.getArgs();

        StringBuilder sb = new StringBuilder();
        for(Object o : param){
            sb.append(o + "; ");
        }
        System.out.println("进入[" + methodName + "]方法,参数为:" + sb.toString());
        // 继续执行方法
        String a=null;
        try {
            a = (String) joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println(methodName + "方法执行结束"+a);

    }

}
