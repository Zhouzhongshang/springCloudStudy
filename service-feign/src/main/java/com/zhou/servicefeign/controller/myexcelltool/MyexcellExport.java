package com.zhou.servicefeign.controller.myexcelltool;

import com.github.liaochong.myexcel.core.DefaultExcelBuilder;
import com.github.liaochong.myexcel.utils.AttachmentExportUtil;
import com.zhou.servicefeign.pojo.ArtCrowdExportVo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

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
     *
     * @param response
     * @param
     * @throws Exception
     */
    @GetMapping("excel")
    public void defaultBuild(HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println("周中山");

      /* String titles [] = {"岗位","服务场次","服务时长"};
        HSSFWorkbook wb;
        OutputStream output = null;
        try {
        wb = new HSSFWorkbook();
        //创建sheet
            String fileName =  "zzs";
        HSSFSheet sh = wb.createSheet(fileName);

        Date date = new Date();


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        fileName += "_" + df.format(date) + ".xls";

        HSSFCellStyle style_title = wb.createCellStyle();
        Font titleFont = wb.createFont();
        titleFont.setItalic(true);
        titleFont.setColor(Font.COLOR_NORMAL);
        titleFont.setFontHeightInPoints((short) 12);
        titleFont.setFontName("仿宋");
        titleFont.setUnderline(Font.U_NONE);
        style_title.setFont(titleFont);

        HSSFCellStyle style_common = wb.createCellStyle();
        Font common_font = wb.createFont();
        common_font.setColor(Font.COLOR_NORMAL);
        common_font.setFontHeightInPoints((short) 10);
        common_font.setFontName("微软雅黑");
        style_common.setFont(common_font);


            // 设置列宽
            for (int i = 0; i < titles.length - 1; i++) {
                sh.setColumnWidth(i, 256 * 15 + 184);
            }

            HSSFRow row = sh.createRow(0);
            HSSFCell cell = null;

            // 第1行
            HSSFRow row3 = sh.createRow(0);

            // 第1行的列
            for (int i = 0; i < titles.length; i++) {
                cell = row3.createCell(i);
                cell.setCellValue(new HSSFRichTextString(titles[i]));
                cell.setCellStyle(style_title);
                sh.autoSizeColumn(i, true);
            }

            List<Map<String, Object>> result = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                Map<String, Object> tempMap = new HashMap<>();
                tempMap.put("岗位", "岗位1");
                tempMap.put("服务场次", "岗位2");
                tempMap.put("服务时长", "岗位3");
                result.add(tempMap);
            }

            //填充数据的内容
            int i = 1, z = 0;
            while (z < result.size()) {
                row = sh.createRow(i);
                Map<String, Object> map = result.get(z);
                for (int j = 0; j < titles.length; j++) {
                    cell = row.createCell(j);
                    if (map.get(titles[j]) != null) {
                        cell.setCellValue(map.get(titles[j]).toString());
                        cell.setCellStyle(style_common);
                        sh.autoSizeColumn(j, true);
                    } else {
                        cell.setCellValue("");
                        cell.setCellStyle(style_common);
                        sh.autoSizeColumn(j, true);
                    }
                }
                i++;
                z++;
            }


            output = response.getOutputStream();
            response.reset();
            response.setCharacterEncoding("UTF-8");
            // 设置contentType为excel格式
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "Attachment;Filename=" + new String(fileName.getBytes(), "iso-8859-1"));
            wb.write(output);
            output.flush();
            output.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
        /*Workbook workbook = DefaultExcelBuilder.of(ArtCrowdExportVo.class)
                .build(getDataList());
        AttachmentExportUtil.export(workbook, "国家信息", response);*/
    }

    /**
     * @Description: 如果为空的数据，则是模板。如果有数据则为导出
     * @Param:
     * @return:
     * @Author: 86157
     * @Date: 2020/3/11
     * @Implementation:
     */
    private List<ArtCrowdExportVo> getDataList() {
        ArrayList<ArtCrowdExportVo> objects = new ArrayList<>();
        // objects.add(new ArtCrowdVo());
        for (int i = 0; i < 10; i++) {
            ArtCrowdExportVo artCrowdVo = new ArtCrowdExportVo();
            artCrowdVo.setDataName("数据名称" + i);
            artCrowdVo.setExt1("备注" + i);
            artCrowdVo.setAreas("海外区域" + i);
            objects.add(artCrowdVo);
        }
        return objects;
    }
}
