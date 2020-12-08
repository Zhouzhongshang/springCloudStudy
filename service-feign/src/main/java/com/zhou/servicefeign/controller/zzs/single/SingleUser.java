package com.zhou.servicefeign.controller.zzs.single;

/**
 * @program: sc-f-chapter1
 * @description: 单例模式demo
 * @author: zzs
 * @create: 2020-12-02 23:04
 **/
public class SingleUser {

    private SingleUser(){
    }

    static enum UserEnum {
        //枚举对象
        INSTANCE;

        private SingleUser singleUser;

        /**
         * 枚举类只会被实例化一次
         */
        private UserEnum(){
            singleUser = new SingleUser();
        }

        public SingleUser get(){
            return singleUser;
        }
    }

    public static SingleUser getInstance(){
        return UserEnum.INSTANCE.singleUser;
    }


}
