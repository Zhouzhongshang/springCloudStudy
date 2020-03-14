package com.zhou.servicefeign.controller.myexcelltool;

import com.github.liaochong.myexcel.core.DefaultExcelBuilder;
import com.github.liaochong.myexcel.utils.AttachmentExportUtil;
import com.zhou.servicefeign.pojo.ArtCrowdExportVo;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: sc-f-chapter1
 * @description: 导出操作  同时支持动态导出：https://github.com/liaochong/myexcel/wiki/%E5%8A%A8%E6%80%81%E5%AF%BC%E5%87%BA
 * @author: zzs
 * @create: 2020-03-11 20:46
 **/
@RestController
@RequestMapping("export")
public class MyexcellExport {

    /**
     * 附件导出
     * @param response
     * @param
     * @throws Exception
     */
    @GetMapping("excel")
    public void defaultBuild(HttpServletResponse response) throws Exception {
        Workbook workbook = DefaultExcelBuilder.of(ArtCrowdExportVo.class)
                .build(getDataList());
      AttachmentExportUtil.export(workbook, "国家信息", response);
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
    private List<ArtCrowdExportVo> getDataList() {
        ArrayList<ArtCrowdExportVo> objects = new ArrayList<>();
     // objects.add(new ArtCrowdVo());
        for (int i = 0 ; i< 10 ; i++){
            ArtCrowdExportVo artCrowdVo = new ArtCrowdExportVo();
            artCrowdVo.setDataName("数据名称"+i);
            artCrowdVo.setExt1("备注"+i);
            artCrowdVo.setAreas("海外区域"+i);
            objects.add(artCrowdVo);
        }
        return objects;
    }
}
