package com.zhou.servicefeign.controller.myexcelltool;

import com.github.liaochong.myexcel.core.DefaultExcelBuilder;
import com.github.liaochong.myexcel.utils.AttachmentExportUtil;
import com.zhou.servicefeign.pojo.ArtCrowdVo;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: sc-f-chapter1
 * @description: 导出操作
 * @author: zzs
 * @create: 2020-03-11 20:46
 **/
@RestController
@RequestMapping("export")
public class MyexcellExport {

    @PostMapping("excel")
    public void defaultBuild(HttpServletResponse response, @RequestBody List<ArtCrowdVo> dataList) throws Exception {
      //  List<ArtCrowdVo> dataList = this.getDataList();
        Workbook workbook = DefaultExcelBuilder.of(ArtCrowdVo.class)
                .build(dataList);
        AttachmentExportUtil.export(workbook, "艺术生信息", response);
    }

    /**
     * @Description: 如果为空的数据，则是模板。如果有数据则为导出
     * @Param: 
     * @return: 
     * @Author: 86157
     * @Date: 2020/3/11
     * @Implementation: 
     *
     */
    private List<ArtCrowdVo> getDataList() {
        ArrayList<ArtCrowdVo> objects = new ArrayList<>();
    //    objects.add(new ArtCrowdVo());
        /*for (int i = 0 ; i< 10 ; i++){
            ArtCrowdVo artCrowdVo = new ArtCrowdVo();
            artCrowdVo.setDataName(""+i);
            artCrowdVo.setExt1(""+i);
            artCrowdVo.setAreas(""+i);
            artCrowdVo.setDataValue(""+i);
            artCrowdVo.setParentid(""+i);
            artCrowdVo.setDictName(""+i);
            objects.add(artCrowdVo);
        }*/
        return objects;
    }
}
