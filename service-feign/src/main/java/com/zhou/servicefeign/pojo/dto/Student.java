package com.zhou.servicefeign.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @program: sc-f-chapter1
 * @description: 学生
 * @author: zzs
 * @create: 2020-05-15 21:50
 **/
@Data
@TableName("student")
public class Student {

    @TableId
    private  String s_id;
    private  String s_name;
    private  String s_birth;
    private  String s_sex;

}
