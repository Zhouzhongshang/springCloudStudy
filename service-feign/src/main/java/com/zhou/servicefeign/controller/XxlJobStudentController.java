package com.zhou.servicefeign.controller;

import com.zhou.servicefeign.pojo.XxlJobStudent;
import com.zhou.servicefeign.service.XxlJobStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 86157
 */
@RestController
@RequestMapping("xxlJobStudent/")
public class XxlJobStudentController {

    @Autowired
    private XxlJobStudentService xxlJobStudentService;
    @PostMapping("/insert")
    public String insert( @RequestBody XxlJobStudent xxlJobStudent){
        return xxlJobStudentService.insert(xxlJobStudent);
    }

}
