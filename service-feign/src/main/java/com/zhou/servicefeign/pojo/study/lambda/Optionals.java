package com.zhou.servicefeign.pojo.study.lambda;

import com.zhou.servicefeign.pojo.XxlJobStudent;

import java.util.ArrayList;
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
        Integer score = getScore(null);
        XxlJobStudent nullIdStudent = new XxlJobStudent();
        Integer score1 = getScore(nullIdStudent);
        Consumer consumer = System.out::println;
        consumer.accept(result);

        XxlJobStudent xxlJobStudent1 = new XxlJobStudent(0, null, 0, "");
        ArrayList<XxlJobStudent> objects = new ArrayList<>();
     // boolean validList=  validList(objects);
        boolean f=valid(xxlJobStudent1);

    }

/*    private static boolean validList(ArrayList<XxlJobStudent> objects) {
        Optional.ofNullable(objects).filter(ArrayList::isEmpty).map(i)
    }*/

    private static boolean valid(XxlJobStudent xxlJobStudent1) {
        return Optional.ofNullable(xxlJobStudent1).map(XxlJobStudent::getName).isPresent();
    }

    private static Integer getScore(XxlJobStudent xxlJobStudent) {
        Integer integer = Optional.ofNullable(xxlJobStudent).map(XxlJobStudent::getId).orElse(0);
        return integer;
    }
}
