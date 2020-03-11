package com.zhou.servicefeign.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: sc-f-chapter1
 * @description: tohtml
 * @author: zzs
 * @create: 2020-03-11 20:22
 **/
@Controller
@RequestMapping("web")
public class WebController {

    @RequestMapping("toHtml")
    public String getHtml(){
         return "excelImport";
    }

}
