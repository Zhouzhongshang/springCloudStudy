package com.zhou.servicefeign.controller.zzs.calculator;


import java.util.*;
import java.util.stream.Stream;

/**
 * @program: sc-f-chapter1
 * @description: 面试题分享
 * @author: zzs
 * @create: 2020-12-14 21:13
 **/
public class InterviewQuestions {
    public static void main(String[] args) {
        String[] arr = {"1","2","3","3"};

        //1.添加到set集合、2.set集合添加到数组
        Set<String> stringSet = new HashSet<>(Arrays.asList(arr));
        String [] results = new String[stringSet.size()];
        int j = 0;
        for (String s : stringSet){
            results[j] = s;
            System.out.println("results[j]:"+results[j]);
            j++;
        }



        //遍历set集合
        Set hashSet = new HashSet<>(Arrays.asList(arr));

        for( Object string : hashSet ){  //1or循环
            String value =(String) string;
        }

        Iterator iterator = hashSet.iterator();//2.迭代器
        while (iterator.hasNext()){
            String next = (String) iterator.next();
        }

        //遍历map
        HashMap<String, String> objectObjectHashMap = new HashMap<>();
        for (String key:objectObjectHashMap.keySet()){
            System.out.println("key:"+key+"value"+objectObjectHashMap.get(key));
        }


        //遍历map
        Set<Map.Entry<String, String>> entries = objectObjectHashMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator1 = entries.iterator();
        while (iterator1.hasNext()){
            Map.Entry<String, String> next = iterator1.next();
            String key = next.getKey();
            String value = next.getValue();
        }

        //遍历map
        for (Map.Entry<String,String> entry:objectObjectHashMap.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
        }

        //jdk1.8遍历map
        objectObjectHashMap.forEach((key,value)->{
            System.out.println(key+":"+value);
        });

        //遍历key  value的方式
        Collection<String> values = objectObjectHashMap.values();
        Set<String> strings = objectObjectHashMap.keySet();

        //hashSet转化为数组的方式
        String[] objects = (String[]) hashSet.toArray(new String[0]);

        //去重后转化为数组
        String[] objects1 = Stream.of(arr).distinct().toArray(String[]::new);


        for (int i = 0 ;i<objects1.length;i++){
            System.out.println(objects1[i]);
        }
    }
}
