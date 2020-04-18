package com.zhou.servicefeign.controller.testhttpclient;

import com.zhou.servicefeign.pojo.Student;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: sc-f-chapter1
 * @description: 测试httpclient的使用
 * @author: zzs
 * @create: 2020-02-24 21:54
 **/
@RestController
@RequestMapping("testHttpClient")
public class TestHttpClientDemo {

    @PostMapping("getStudent")
    public List<Student> getStudent( @RequestBody Student studentBo){

        TestMultithreading testMultithreading = new TestMultithreading();
        //开启一个线程去做其他事情 主线程继续会执行任务。
        new Thread(testMultithreading).start();

        List<Student> students=new ArrayList<>(3);
        for (int i = 0; i <3 ; i++) {
            Student student = new Student();
            student.setId(String.valueOf(i));
            student.setName(studentBo.getName());
            student.setHobby(studentBo.getHobby());
            students.add(student);
        }
        return students;
    }

}
