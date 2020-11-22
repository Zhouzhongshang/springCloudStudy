package com.zhou.servicefeign.controller.wjj;

import com.zhou.servicefeign.dao.dao.TestDbDao;
import com.zhou.servicefeign.pojo.dto.entity.TestDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @program: sc-f-chapter1
 * @description: 测试
 * @author: zzs
 * @create: 2020-11-22 20:04
 **/
@RestController
public class TestMydataBase {

    @Autowired
    private TestDbDao testDbDao;

    @GetMapping("test011")
    public List<TestDb> test(){
        TestDb testDb = new TestDb();
        testDb .setId(1);
return testDbDao.queryAll(testDb);
    }

    @PostMapping("insert01")
    public void insert(){
        TestDb testDb = new TestDb();
//        testDb.setIsDelete(0);
        testDb.setCreateTime(LocalDateTime.now());
        testDb.setUpdateTime(LocalDateTime.now());
        testDb.setName("zzs");
        testDbDao.insert(testDb);
    }
}
