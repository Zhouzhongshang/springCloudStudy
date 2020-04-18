package com.zhou.servicefeign.controller.wjj;

import com.zhou.servicefeign.dao.wjj.BossPostDao;
import com.zhou.servicefeign.pojo.wjj.entity.BossPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: sc-f-chapter1
 * @description: wjjcontroller
 * @author: zzs
 * @create: 2020-04-05 15:56
 **/
@RestController
@RequestMapping("/wjj")
public class BossPostController {

    @Autowired
    private BossPostDao bossPostDao;

    @GetMapping("insertData")
    public void insertBatch(){
        List<BossPost> bossPosts = BossPost.getBossPosts();
         bossPosts.forEach(i->{
             bossPostDao.insert(i);
         });
    }

}
