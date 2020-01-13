package com.zhou.servicefeign.job;

import com.alibaba.fastjson.JSON;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import com.zhou.servicefeign.pojo.XxlJobStudent;
import com.zhou.servicefeign.service.XxlJobStudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 86157
 */
@Component
public class SampleXxlJob {
    private static Logger logger = LoggerFactory.getLogger(SampleXxlJob.class);

/*    @Value("${xxl.job.executor.port}")
    private int port;*/

    @Autowired
    private XxlJobStudentService xxlJobStudentService;

    /**
     * 任务名称给予方法的形式
     * @param param
     * @return
     * @throws Exception
     */
    @XxlJob("demoJobHandler01")
    public ReturnT<String > demoJobHandler(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB, Hello World.");
/*        XxlJobLogger.log("cloud机器："+String.valueOf(port));*/
        XxlJobStudent xxlJobStudent = new XxlJobStudent();
        xxlJobStudent.setName("zzs");
        xxlJobStudent.setAge(27);
        xxlJobStudent.setHobby("打飞机");
        xxlJobStudentService.insert(xxlJobStudent);
        String s = JSON.toJSONString(xxlJobStudent);
        return new ReturnT<String >(s);
    }
}
