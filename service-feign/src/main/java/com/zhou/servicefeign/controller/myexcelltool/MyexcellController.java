package com.zhou.servicefeign.controller.myexcelltool;

import com.github.liaochong.myexcel.core.SaxExcelReader;
import com.zhou.servicefeign.pojo.ArtCrowdVo;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @program: sc-f-chapter1
 * @description: 做exell的功能测试:导出
 * @author: zzs
 * @create: 2020-03-06 15:16
 **/
public class MyexcellController {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\86157\\Desktop\\Country Region.xlsx");
        // （推荐）方式一：全部读取后处理，SAX模式，避免OOM，建议大量数据使用
        List<ArtCrowdVo> result = SaxExcelReader.of(ArtCrowdVo.class).sheets("1", "2").read(file);

        /**
         * 实现思路：先建立好关系再一次性全部入库
         * 第一步先做父节点处理：ext1 = dataName,areas=0,dataValue = 330 + 10 ,parentid  = 0,dictName = "海外区域"
         *
         * 第二步做子节点处理：1先找到父节点，2 建立关系：.parentid=dataValue. dataName英文 ext1中文 areas这里不变
         *   dataValue = 在区域的基础上继续加10吧
         *   dictName=“海外国家”
         */
        List<ArtCrowdVo> parentList = result.stream().filter(i -> i.getExt1() == null).distinct().collect(Collectors.toList());
        List<ArtCrowdVo> parentResult = new ArrayList<>();
        HashMap<String, ArtCrowdVo> artCrowdVoHashMap = new HashMap<>(400);
        int dataValue = 330;
        for (ArtCrowdVo artCrowdVo : parentList) {
            ArtCrowdVo parentArtCrowdVo = new ArtCrowdVo(artCrowdVo.getDataName(), artCrowdVo.getDataName(), "0", String.valueOf(dataValue), "0", "海外区域");
            dataValue += 10;
            parentResult.add(parentArtCrowdVo);
            artCrowdVoHashMap.put(parentArtCrowdVo.getDataName(), parentArtCrowdVo);
        }

        //剩下的为子元素数据
        result.removeAll(parentList);

        List<ArtCrowdVo> childResult = new ArrayList<>();
        for (ArtCrowdVo artCrowdVo : result) {
            String dataValue1 = artCrowdVoHashMap.get(artCrowdVo.getAreas()).getDataValue();
            ArtCrowdVo childArtCrowdVo = new ArtCrowdVo(artCrowdVo.getDataName(), artCrowdVo.getExt1(), artCrowdVo.getAreas(), String.valueOf(dataValue), dataValue1, "国外国家");
            childResult.add(childArtCrowdVo);
            dataValue += 10;
        }

        parentResult.addAll(childResult);

        parentResult.stream().forEach(i -> {
            System.out.println(i.toString());
        });

    }
}
