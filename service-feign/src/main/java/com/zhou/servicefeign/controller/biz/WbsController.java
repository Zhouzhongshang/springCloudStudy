package com.zhou.servicefeign.controller.biz;

import com.zhou.servicefeign.dao.WbsDao;
import com.zhou.servicefeign.pojo.WbsDto;
import com.zhou.servicefeign.service.biz.WbsService;
import com.zhou.servicefeign.service.biz.impl.WbsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * @author 86157
 */
@RestController
@RequestMapping("wbs/")
public class WbsController {

    @Autowired
    private WbsService wbsService;

    @Autowired
    private WbsServiceImpl wbsServiceImpl;

    @Autowired(required = false)
    private WbsDao wbsDao;

    /**
     * 批量添加数据List接受
     */
    @PostMapping("/wbsBatchSaveListTest")
    public void wbsBatchSaveListTest(@RequestBody List<WbsDto> wbsDtoList) {
        //两个参数 一个参数为有结构的数据 一个参数是没有结构的数据
        boolean b = wbsServiceImpl.saveBatch(wbsDtoList);
        System.out.println(b);
    }

    /**
     * 批量添加数据 Map接受
     */
    @PostMapping("/wbsBatchSaveMapTest")
    public void wbsBatchSaveMapTest(@RequestBody Map paramas) {
        //两个参数 一个参数为有结构的数据 一个参数是没有结构的数据

    }

    @GetMapping("/get")
    public List<WbsDto> get(){
        return wbsServiceImpl.list();
    }

/*
    *//**
     * 组织树查询 这个方法也不行
     *//*
    @GetMapping("list")
    public ArrayList<WbsDto> list() {
        //返回组织好的数据
        List<WbsDto> list = wbsServiceImpl.list();
        ArrayList<WbsDto> objects1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            WbsDto wbsDto = list.get(i);
            String itemNo = wbsDto.getItemNo();
            String[] split = itemNo.split("\\.");
            int size = split.length;
            if (1 == size) {
                objects1.add(list.get(i));
            }
            ArrayList<WbsDto> objects2 = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                WbsDto wbsDto1 = list.get(j);
                String itemNo1 = wbsDto1.getItemNo();
                String[] split1 = itemNo1.split("\\.");
                int size1 = split1.length;
                if (1 == size && 2 == size1 && split[0].equals(split1[0])) {
                    objects2.add(list.get(j));
                }
                ArrayList<WbsDto> objects3 = new ArrayList<>();
                for (int k = 0; k < list.size(); k++) {
                   WbsDto wbsDto2 = list.get(k);
                    String itemNo2 = wbsDto2.getItemNo();
                    String[] split2 = itemNo2.split("\\.");
                    int size2 = split2.length;
                    if (3 == size2 && 2 == size1 && 1 == size && split2[1].equals(split1[1])) {
                        objects3.add(list.get(k));
                    }
                }

            }
        }
        return objects1;
    }*/
}
/*
        Map<Integer ,WbsDto> resultMap= convertToResulMap(list);
        return resultMap;
    }

    *//**
     * 对数据进行处理
     *//*
    private Map<Integer ,WbsDto> convertToResulMap(List<WbsDto> list) {
        *//**
         * 遍历list拿到itemNo 对编号进行截取处理
         *        :长度为1的直接放到resultTeeMap  key：itemNo  Value:WbsDto
         *        ：长度为2的放到声明的temp2Map中 key: itemNo[1] value:WbsDto
         *        ：长度为3的放到声明的temp3Map中 key: item[2]   value:WbsDto
         *
         *         如果1组和2组的【2】相同呢？？？？？？？？？？？？？？
         *
         *        这里存混合了啊？？？？？？？？？？？
         *
         *
         *  组装好下面的数据：
         *
         * 遍历temp3Map 同时声明一个treeMap<Integer,treeMap<Inter,Dto> >拿到itemNo对编号进行截取处理[1]
         *       :如果Map中包含编号 拿到值 直接将 Map（key:[2] value[dto]）添进去  key：[1] value：Map
         *            不包含编号 new treeMap<Inter(3),Dto>  添加进去 key[2] value: 前面声明的MAp
         *
         * 最后遍历这个treeMap去temp2Map中找 找到直接 拿到Dto.setMap
         *
         * 再遍历temp2Map 重复上面的步骤   最后得到resultTeeMap
         *
         *
         *//*

        Map<Integer ,WbsDto> resultTeeMap =  new TreeMap<>();
        Map<Integer ,WbsDto> temp2Map =  new TreeMap<>();
        Map<Integer ,WbsDto> temp3Map =  new TreeMap<>();

        for(WbsDto wbsDto :list){
            String itemNo = wbsDto.getItemNo();
            String[] split = itemNo.split(".");
            if (1 == split.length){
                resultTeeMap.put(Integer.parseInt(split[0]),wbsDto);
            }else if (2 == split.length){
                temp2Map.put(Integer.parseInt(split[1]),wbsDto);
            }else if(3 == split.length){
                temp3Map.put(Integer.parseInt(split[2]),wbsDto);
            }else {
                System.out.println("输入的不合法的项目编号");
            }
        }

         Map<Integer,Map<Integer,WbsDto>>  temp3ResultMap=  handle(temp3Map);



        return null;
    }

    *//**
     * 主要是对结果分组 返回的数据key是相同的 value是Map 只能满足第一个序号相同的插入
     *   1.1.1
     *   1.1.2
     *   1.2.3
     *   1.2.4
     *
     *   2.2.3这样子分组就乱了
     *   2.1.3
     *    感觉思路又错了
     * @param temp3Map
     * @return
     *//*
    private Map<Integer, Map<Integer,WbsDto>> handle(Map<Integer, WbsDto> temp3Map) {
      Map<Integer,Map<Integer,WbsDto>> temp3ResultMap=  new TreeMap<>();
        for(Map.Entry<Integer, WbsDto> mapEntity:temp3Map.entrySet()){
            System.out.println("键是"+mapEntity.getKey());
            System.out.println("值是"+mapEntity.getValue());
            String itemNo = mapEntity.getValue().getItemNo();
            String[] split = itemNo.split(",");
           //bug 第一组和第二组的keY相等呢？
            if (temp3ResultMap.containsKey(split[1])){
                temp3ResultMap.get(split[1]).put(Integer.parseInt(split[2]),mapEntity.getValue());
            }else{
                Map<Integer,WbsDto> tempMap= new TreeMap<>();
                tempMap.put(Integer.parseInt(split[2]),mapEntity.getValue());
                temp3ResultMap.put(Integer.parseInt(split[1]),tempMap);
            }
        }
       return temp3ResultMap;
    }*/
