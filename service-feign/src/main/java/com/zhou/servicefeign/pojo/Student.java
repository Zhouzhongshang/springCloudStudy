package com.zhou.servicefeign.pojo;


import lombok.Data;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

    @Data
    public class Student {
        private String id;
        private String name;
        private String hobby;

        public Student(String id, String name, String hobby) {
            this.id = id;
            this.name = name;
            this.hobby = hobby;
        }

        public static void main(String[] args) {
            List<Student> studentList = new ArrayList<>();
            studentList.add(new Student("1", "张三", "张三的爱好"));
            studentList.add(new Student("2", "小明", "打飞机"));
            studentList.add(new Student("1.1", "李四", "李四的爱好"));
            studentList.add(new Student("1.1.1", "小红", "看书"));
            studentList.add(new Student("1.12.1", "小红", "看书"));
            studentList.add(new Student("1.2", "小黑", "开黑"));
            studentList.add(new Student("3","3的名字","3的爱好"));
            studentList.add(new Student("3.1","3.1的名字","3.1的爱好"));
            studentList.add(new Student("3.21.1","3.1的名字","3.1的爱好"));
            studentList.add(new Student("1.2.1", "小王", "游泳"));
            studentList.add(new Student("2.1", "小钱", "钱钱钱"));
            studentList.add(new Student("2.1.1", "小孙", "孙孙孙"));
            List<DataVo> list = new ArrayList<>();
            sort(1, studentList, list, null);
            System.out.println(JSON.toJSONString(list));
            List<Student> students= new ArrayList<>();
            structureToList(list,students);
            System.out.println(students);
        }

        /**
         * 来对listStucture转换成list
         * @param list
         * @param students
         */
        private static void structureToList(List<DataVo> list, List<Student> students) {
            /**
             * 0，需要干什么
             * 1,需要一个结束条件
             * 2，需要函数关系
             */
            if (list.isEmpty()){
                return;
            }
            for (DataVo dataVo:list){
                Student student = new Student();
                BeanUtils.copyProperties(dataVo,student,Student.class);
                students.add(student);
                List<DataVo> nextList = dataVo.getNextList();
                structureToList(nextList,students);
            }
        }

        public static void sort(int level, List<Student> studentList, List<DataVo> list, String parentId) {
            if (CollectionUtils.isEmpty(studentList)) {
                return;
            }
            List<Student> all = new ArrayList<>(studentList);
            int finalLevel = level;
            List<Student> studentListTemp = studentList.stream().filter(student -> student.id.split("\\.").length == finalLevel).sorted(Comparator.comparingInt(o -> Integer.parseInt(o.getId().split("\\.")[finalLevel-1]))).collect(Collectors.toList());
            if (StringUtils.isNotBlank(parentId)) {
                studentListTemp = studentListTemp.stream().filter(student -> student.getId().startsWith(parentId)).collect(Collectors.toList());
            }
            level = level + 1;
            for (Student student : studentListTemp) {
                DataVo dataVo = new DataVo(student);
                List<DataVo> nextList = new ArrayList<>();
                sort(level, all, nextList, dataVo.getId());
                dataVo.setNextList(nextList);
                list.add(dataVo);
            }
        }

        @Data
        static
        class DataVo {

            private String id;
            private String name;
            private String hobby;

            private List<DataVo> nextList;

            public DataVo(Student student) {
                this.id = student.getId();
                this.name = student.getName();
                this.hobby = student.getHobby();
            }
        }
    }
