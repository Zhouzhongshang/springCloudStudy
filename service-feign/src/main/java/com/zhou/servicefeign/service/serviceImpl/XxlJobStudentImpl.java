package com.zhou.servicefeign.service.serviceImpl;

import com.zhou.servicefeign.dao.XxlJobStudentDao;
import com.zhou.servicefeign.pojo.XxlJobStudent;
import com.zhou.servicefeign.service.XxlJobStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 86157
 */
@Service
public class XxlJobStudentImpl implements XxlJobStudentService {
    @Autowired
    private XxlJobStudentDao xxlJobStudentDao;

    @Override
    public String insert(XxlJobStudent xxlJobStudent) {
        int i=xxlJobStudentDao.insert(xxlJobStudent);
        return String.valueOf(xxlJobStudent.getId());
    }
}
