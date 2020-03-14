package com.zhou.servicefeign.controller.myexcelltool;

import com.github.liaochong.myexcel.core.SaxExcelReader;
import com.zhou.servicefeign.pojo.ArtCrowdVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @program: sc-f-chapter1
 * @description: 做excel导入操作
 * @author: zzs 支持两种格式
 * @create: 2020-03-11 19:56
 **/
@RestController
@RequestMapping("excell")
public class MyexcellImport {

    @PostMapping(value = "/excelImport")
    public List<ArtCrowdVo> importExcell(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        if (file.isEmpty()) {
            return null;
        }
       //InputStream inputStream = file.getInputStream();
        // 方式一：全部读取后处理，SAX模式，避免OOM，建议大量数据使用
        List<ArtCrowdVo> result = SaxExcelReader.of(ArtCrowdVo.class).sheet(0).rowFilter(i -> i.getRowNum()>1 ).read(file.getInputStream());
               // .sheet(0) // 0代表第一个，如果为0，可省略该操作，也可sheet("名称")读取，.csv文件无效
             //   .rowFilter(row -> row.getRowNum() > 0) // 如无需过滤，可省略该操作，0代表第一行
              //  .charset("GBK") // 目前仅.csv文件有效，设置当前文件的编码
       //         .read(inputStream);// 可接收inputStream
        return result;
    }
}
