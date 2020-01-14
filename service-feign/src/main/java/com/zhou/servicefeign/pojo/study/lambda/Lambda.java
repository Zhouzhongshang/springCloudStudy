package com.zhou.servicefeign.pojo.study.lambda;

import com.zhou.servicefeign.pojo.XxlJobStudent;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author 86157
 *
 * 去重、按照字母来排序、转化为小写、长度大于5的
 */
public class Lambda {

    public static void main(String[] args) {
        //测试1
        test1();

        //构造流
        construct();

        //数值流的构造
        valueConstruct();

        //流转化为其他数据结构
        //convert();

        //使用
        use();

        //遍历
        select();

        //分组


        //
        String[] array = {"a","b","c"};

        String [] arr ={"1", "2", "bilibili", "of", "codesheep", "5", "at", "BILIBILI", "codesheep", "23", "CHEERS", "6"};

        List<String> list= new ArrayList<>(Arrays.asList(arr));

        /**
         * lambda编程
         */
        String collect = list.stream()
                .filter(o -> !isNum(o))
                .filter(o -> o.length() >= 5)
                .map(o -> o.toLowerCase())
                .distinct()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining("@"));

        System.out.println(collect);
        /**
         * 定义按照字母顺序的容器 set可以去重
         */
        TreeSet stringSet =new TreeSet<String>(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                }
        );

// 以下for循环完成元素去重、大小写转换、长度判断等操作
        for( int i=0; i<list.size(); i++ ) {
            String s = list.get(i);
            if( !isNum(s) && s.length()>=5 ) {
                // 统一转小写
                String sLower = s.toLowerCase();
                stringSet.add( sLower );
            }
        }
        // 以下for循环完成连词成句
        StringBuilder result = new StringBuilder();
        for( Object s : stringSet ) {
            result.append( s );
            // 用“爱心”连接符连接
            result.append("❤");
        }
        // 去掉最后一个多余连接符
        String finalResult = result.substring(0,result.length()-1).toString();

        System.out.println(finalResult);

    }

    /**
     * 按照年龄分组
     */


    /**
     * peek(中间操作)和forEach(终端操作)
     */
    private static void select() {
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }

    /**
     * 流的使用
     *   :总结
     *   .map(i -> i.toUpperCase())
     *   .map(String::toUpperCase) 用法一样
     */
    private static void use() {
        String [] arr ={"1", "2", "bilibili", "of", "codesheep", "5", "at", "BILIBILI", "codesheep", "23", "CHEERS", "6"};
        // List<String> list=  Stream.of(arr).filter(i -> !isNum(i)).map(i -> i.toUpperCase()).collect(Collectors.toList());
        List<String> list=  Stream.of(arr).filter(i -> !isNum(i)).map(String::toUpperCase).collect(Collectors.toList());
        //String[] array =  Stream.of(arr).filter(i -> !isNum(i)).map(String::toUpperCase).toArray(String[]::new);
        Consumer consumer=   System.out::println;
          consumer.accept(list);
         list.stream().forEach(System.out::println);
    }

    /**
     * 流的转化为其他数据结构
     */
    private static void convert() {
        ArrayList<String> objects = new ArrayList<>();
        Stream<String> stream = objects.stream();
        // 1. Array
        String[] strArray1 = stream.toArray(String[]::new);
       // 2. Collection
        List<String> list1 = stream.collect(Collectors.toList());
        List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
        Set set1 = stream.collect(Collectors.toSet());
        Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));
        // 3. String
        String str = stream.collect(Collectors.joining()).toString();
    }

    /**
     * 数值的流
     */
    private static void valueConstruct() {
        IntStream.of(new int []{1,2,3}).forEach(System.out::println);
        IntStream.of(3,4,5).forEach(System.out::println);
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 3).forEach(System.out::println);
    }

    /**
     * 构造流
     */
    private static void construct() {
        // 1. Individual values
        Stream stream = Stream.of("a", "b", "c");
        // 2. Arrays
        String [] strArray = new String[] {"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        // 3. Collections
        List<String> list1 = Arrays.asList(strArray);
        stream = list1.stream();
    }

    /**
     *根据实体类的age倒序排列，输出他的id,成为集合
     */
    private static void test1() {
        List<XxlJobStudent> xxlJobStudents=new ArrayList<>();
        for (int i=0;i<4;i++){
            XxlJobStudent xxlJobStudent = new XxlJobStudent(i,"周中山",i,"play Code");
            xxlJobStudents.add(xxlJobStudent);
        }
        List<Integer> list=xxlJobStudents.stream().filter(i -> i.getHobby()!=null).sorted(Comparator.comparing(XxlJobStudent::getAge).reversed()).map(XxlJobStudent::getId).collect(Collectors.toList());
       Consumer consumer= System.out::println;
       consumer.accept(list);
    }


    /**
     * 判断字符串是数字还是字母
     * 数字为true
     * 不是false
     * @param str
     * @return
     */
    public static Boolean isNum( String str ) {
        for( int i=0; i<str.length(); i++ ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }



    }
