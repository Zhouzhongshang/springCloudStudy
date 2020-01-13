package com.zhou.servicefeign.controller.hutool.meiju;

/**
 * @author 86157
 * 线程安全的单例模式
 */

public enum SingleInstance {
    INSTANCE;
    public SingleInstance getInstance(){
        return INSTANCE;
    }
}
