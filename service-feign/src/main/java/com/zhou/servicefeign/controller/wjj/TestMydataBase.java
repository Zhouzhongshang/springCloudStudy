package com.zhou.servicefeign.controller.wjj;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhou.servicefeign.dao.dao.TestDbDao;
import com.zhou.servicefeign.pojo.dto.entity.TestDb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @program: sc-f-chapter1
 * @description: 测试
 * @author: zzs
 * @create: 2020-11-22 20:04
 **/
@RestController
@Slf4j
public class TestMydataBase implements Serializable {

    private static final long serialVersionUID = -8836891662583598196L;
    @Autowired
    private TestDbDao testDbDao;

    @GetMapping("test011")
    public List<TestDb> test(){
        TestDb testDb = new TestDb();
        testDb .setId(1);
return testDbDao.queryAll(testDb);
    }

    @PostMapping("insert01")
    @Transactional(rollbackFor = Exception.class)
    public void insert() throws Exception {
        TestDb testDb01 = new TestDb();
        testDb01.setIsDelete(0);
        testDb01.setCreateTime(LocalDateTime.now());
        testDb01.setUpdateTime(LocalDateTime.now());
        testDb01.setName("zzs");
        int insert1 = testDbDao.insert(testDb01);

        TestDb testDb02 = new TestDb();
        testDb02.setIsDelete(0);
        testDb02.setCreateTime(LocalDateTime.now());
        testDb02.setUpdateTime(LocalDateTime.now());
        testDb02.setName("zzs");
        int insert = testDbDao.insert(testDb02);
        if (insert==1){
            throw new Exception("主动跑出异常");
        }
    }
}
