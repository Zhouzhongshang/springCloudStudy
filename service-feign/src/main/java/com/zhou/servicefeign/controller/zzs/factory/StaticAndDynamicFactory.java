package com.zhou.servicefeign.controller.zzs.factory;

/**
 * @program: sc-f-chapter1
 * @description: 工厂
 * @author: zzs
 * @create: 2020-12-09 22:53
 **/
public class StaticAndDynamicFactory {


    /**
     *      Integer instance = Factory.getInstance();
     *             Factory factory = new Factory();
     *             Integer integer1 = factory.getInteger1();
     *
     *
     * 静态工厂
     * <bean class="net.web.ExternalBeanStaticFactory" factory-method="getInstance"
     * id="okHttpClientStatic"></bean>
     *
     *  动态工厂--实例工厂：指定相关方法生成对象、需要更多的内存
     * <bean class="net.web.ExternalBeanFactory" id="ExternalBeanFactory"/>
     * <bean class="okhttp3.OkHttpClient" factory-bean="ExternalBeanFactory"
     * factory-method="getInstance" id="okHttpClient"></bean>
     */

    private static Integer integer;
        //静态工厂
        public static Integer getInstance(){
            if (null == integer){
                return 1;
            }
            return integer;
        }

    private Integer integer1;
        //动态工厂
        public Integer getInteger1(){
            if (integer1== null){
                return 1;
            }
            return integer1;
    }
}
