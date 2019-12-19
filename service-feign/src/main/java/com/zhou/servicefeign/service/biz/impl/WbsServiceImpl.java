package com.zhou.servicefeign.service.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.servicefeign.dao.WbsDao;
import com.zhou.servicefeign.pojo.WbsDto;
import com.zhou.servicefeign.service.biz.WbsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.locks.Condition;

/**
 * @author 86157
 */
@Service
public class WbsServiceImpl extends ServiceImpl<WbsDao,WbsDto> implements WbsService  {

    @Autowired(required = false)
    private WbsDao wbsDao;

    public List<WbsDto> getlist(){
        return null;
    }


}
