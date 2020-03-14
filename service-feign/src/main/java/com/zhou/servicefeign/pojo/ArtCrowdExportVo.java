package com.zhou.servicefeign.pojo;

import com.github.liaochong.myexcel.core.annotation.ExcelColumn;
import com.github.liaochong.myexcel.core.annotation.ExcelModel;
import lombok.Data;

/**
 * @program: sc-f-chapter1
 * @description: 导出vo  设置样式
 * @author: zzs
 * @create: 2020-03-14 19:26
 **/
@Data
@ExcelModel(sheetName = "海外国家数据")
public class ArtCrowdExportVo {

    /**
     * 全球合作伙伴 -> 数据名称  表头
     */
    @ExcelColumn(order = 0,title = "全球合作伙伴 -> 数据名称",style={"title->text-align:center","cell->color:green"})
    public String dataName;

    /**
     * 国家中文
     */
    @ExcelColumn(order = 1,title = "全球合作伙伴 -> 备注",style={"title->text-align:center","cell->color:green"})
    public String ext1;

    /**
     * 国家所属区域
     */
    @ExcelColumn(order = 2,title = "全球合作伙伴 -> 所属区域",style={"title->color:red text-align:center","cell->color:green"})
    public String areas;
}
