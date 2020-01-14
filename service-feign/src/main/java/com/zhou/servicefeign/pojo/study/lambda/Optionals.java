package com.zhou.servicefeign.pojo.study.lambda;

import com.zhou.servicefeign.pojo.XxlJobStudent;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author 86157
 *  告别if else方法
 */
public class Optionals {
    public static void main(String[] args) {
        XxlJobStudent xxlJobStudent = new XxlJobStudent();
        xxlJobStudent.setId(1);
        Integer result = getScore(xxlJobStudent);
        Consumer consumer = System.out::println;
        consumer.accept(result);
    }

    private static Integer getScore(XxlJobStudent xxlJobStudent) {
        return Optional.ofNullable(xxlJobStudent).map(XxlJobStudent::getId).orElse(null);
    }
}
