package com.zhou.servicefeign.controller.zzs.collection;

import java.util.*;

/**
 * @program: sc-f-chapter1
 * @description: 集合源码学习
 * @author: zzs
 * @create: 2021-05-19 10:20
 **/
@SuppressWarnings(value = {"all"})
public class Collection_ {
    public static void main(String[] args) {
        /**
         * ArrayList源码
         * 1.无参构造器时，先创建空的elementData数组，大小为0
         * 2.当添加元素时，首先创建10个长度的数组，然后1.5倍扩容，位移运算。
         *   需要的容量1，原始为0，那么调用扩容算法，1.5倍。
         *   如果新的 -久的大于0，给与默认值为10
         *   后面的则按照1.5倍扩容。
         *
         *   Arrays.copyOf(a,size)
         */
        List list = new ArrayList<>();

        for (int i =1 ; i<=10;i++){
            list.add(i);
        }

        for (int i = 11 ; i<=15;i++){
            list.add(i);
        }

        list.add(16);
        list.add(17);
        list.add(null);


        /**
         * vector源码:
         *    *** Vector<Object> objects1 = new Vector<>(10, 5); 初始化容量，扩容数量，不定义的话默认为0
         *    1.0 无参构造，new的时候直接给默认容量为10，然后按照
         *                int newCapacity = oldCapacity + ((capacityIncrement > 0) ?
         *                                        capacityIncrement : oldCapacity); 即为2倍扩容
         *    2.0 有参构造可以直接指定容量和扩容数量。
         *
         */
        Vector<Object> objects = new Vector<>();
        for (int i =1 ; i<=10;i++){
            objects.add(i);
        }
        objects.add(16);
        objects.add(17);
        objects.add(null);


        /**
         * linkedList双向链表源码：
         * （没看懂过程）
         *    Node first
         *    Node last
         * 链表连接在一起
         */
        LinkedList<Object> objects1 = new LinkedList<>();
        objects1.add(1);

        /**
         * HasHSet源码分析：
         * key是存放的元素，value是一个Object对象。
         * 1.HashSet底层就是HashMap ->构造方法就是new HashMap()
         * 2.添加元素时，先得到hash值，再计算出索引。
         *    【数组长度为16 ，负载因子为0.75 临界值为12。如果数组使用到了12，就会扩容到16*2=32 临界值会变成24。】
         *     临界值指的是，添加的元素的个数
         * 3.找到table位置，是否已经有元素了
         * 4.如果没有，直接加入
         * 5.如果有，则调用equals依次比较，是否相等，如果相同则直接丢弃，不相同则放入在尾部
         * 6.JAVA8中，如果链表的长度达到的8，则会进行树化，树化时，并不是马上树化，会先判断数组长度是否达到64，如果没有会先尝试扩容。有的话则转成红黑树。
         */
        HashSet<Object> set = new HashSet<>();
        for (int i = 1; i<100;i++){
            set.add(new A(i));
        }

        /**
         * HashMap源码分析：
         * 1,key value可以是任何引用的数据，会封装到HashMap$Node中实现了Entry接口，
         *    一对key - value就是一个Entry(有些书)
         *    hash key value Node next4个参数
         * 2,key不允许重复，value可以重复
         * 3，key value都可以为null key不能重复 value可以重复
         */
        HashMap<Object, Object> map = new HashMap<>();
        map.put("松泽","阿木荣");
        map.put("周中山","wjj");

        //遍历
        //1
        Set<Object> objects2 = map.keySet();
        for (Object i : objects2){
            System.out.println(map.get(i));
        }
        Iterator<Object> iterator = objects2.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        //2.
        Collection<Object> values = map.values();
        for (Object v:values){
            System.out.println(v);
        }
        //迭代器

        //3.
        Set<Map.Entry<Object, Object>> entries = map.entrySet();
        for (Map.Entry<Object, Object> entry : entries){
            System.out.println("key_value:"+entry.getKey()+"："+entry.getValue());
        }

        map.forEach((key,value)->{
            System.out.println("key_value:"+key+":"+value);
        });
    }

}

class A{
    private int i;


    public A(int i) {
        this.i = i;
    }

    /**
     * 为了证明当链表长度达到8时，且数组长度达到64时如果没有则先扩容，最后树化，
     * @return
     */
    @Override
    public int hashCode() {
        return 100;
    }
}


/**
 * 如果age和name相同则是相同的
 */
class Student{
   private int age;
   private String name;

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return getAge() == student.getAge() &&
                getName().equals(student.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAge(), getName());
    }
}
