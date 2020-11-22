package com.zhou.servicefeign.controller.manytableselect;

import com.zhou.servicefeign.dao.ManyTableDao;
import com.zhou.servicefeign.pojo.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: sc-f-chapter1
 * @description: 多表查询
 * @author: zzs
 * @create: 2020-05-15 21:58
 **/
@RestController
@RequestMapping("table")
public class ManyTableController {

    @Autowired
    private ManyTableDao manyTableDao;

    @PostMapping("insert")
    public void insert(@RequestBody Student student){
        manyTableDao.insert(student);
    }

}
