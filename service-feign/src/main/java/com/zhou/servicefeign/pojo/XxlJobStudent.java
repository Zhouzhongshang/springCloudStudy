package com.zhou.servicefeign.pojo;

import lombok.Data;

import java.util.function.Supplier;

/**
 * @author 86157
 */
@Data
public class XxlJobStudent implements Supplier<XxlJobStudent> {

    private int id;

    private String name;

    private int age;

    private String hobby;

    public XxlJobStudent() {
    }

    public XxlJobStudent(int id, String name, int age, String hobby) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.hobby = hobby;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getHobby() {
        return hobby;
    }

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public XxlJobStudent get() {
        return null;
    }


    public static void main(String[] args) {


    }
}
