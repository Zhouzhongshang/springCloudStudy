package com.zhou.servicefeign.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhou.servicefeign.pojo.dto.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @program: sc-f-chapter1
 * @description:
 * @author: zzs
 * @create: 2020-05-15 22:01
 **/
@Repository
public interface ManyTableDao extends BaseMapper<Student> {
}
