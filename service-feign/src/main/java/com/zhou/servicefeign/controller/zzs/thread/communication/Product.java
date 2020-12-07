package com.zhou.servicefeign.controller.zzs.thread.communication;

/**
 * @program: sc-f-chapter1
 * @description: 商品 共享资源
 * @author: zzs
 * @create: 2020-12-07 15:32
 **/
public class Product {
    public String name;
    public String color;

    public String getName() {
        return name;
    }

    public Product(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Product() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
