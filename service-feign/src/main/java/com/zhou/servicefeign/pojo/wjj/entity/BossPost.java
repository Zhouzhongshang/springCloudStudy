package com.zhou.servicefeign.pojo.wjj.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * (BossPost)实体类
 *
 * @author makejava
 * @since 2020-04-05 15:52:34
 */
@Data
@TableName("boss_post")
public class BossPost {

    private Integer id;
    
    private String post;
    
    private String company;
    
    private String rank;
    
    private String position;
    
    private String year;
    
    private String education;
    
    private String img;
    
    private String name;
    
    private String job;
    
    private String salary;

    public BossPost( Integer id, String post, String company, String rank, String position, String year, String education, String img, String name, String job, String salary) {
        this.id = id;
        this.post = post;
        this.company = company;
        this.rank = rank;
        this.position = position;
        this.year = year;
        this.education = education;
        this.img = img;
        this.name = name;
        this.job = job;
        this.salary = salary;
    }

    public static List<BossPost> getBossPosts(){
     List<BossPost> bossPosts=  new ArrayList<>();
         //1,后端工程师 2，腾讯
        // 1,前端工程师，2阿里
        //1,测试工程师，2网易
     for ( int i = 1; i< 201;i++){
         BossPost bossPost;
       if (i%3 == 0){
            bossPost = new BossPost(i,"后端工程师" + i, "腾讯", String.valueOf(i), "浙江", "2", "本科", "图片", "万娇娇" + i, "后端总监", "15K");
           bossPosts.add(bossPost);
       }
       if (i%3 == 1){
           bossPost = new BossPost(i,"前端工程师" + i, "阿里", String.valueOf(i), "浙江", "2", "本科", "图片", "千娇娇" + i, "前端总监", "15K");
           bossPosts.add(bossPost);

       }
       if (i%3==2){
           bossPost = new BossPost(i,"测试工程师" + i, "网易", String.valueOf(i), "浙江", "2", "本科", "图片", "百娇娇" + i, "测试总监", "15K");
           bossPosts.add(bossPost);
       }
     }
     return bossPosts;
    }


}