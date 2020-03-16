package com.zhou.servicefeign.controller.annotationdemo.test;

import com.zhou.servicefeign.controller.annotationdemo.LoginRequired;
import com.zhou.servicefeign.controller.annotationdemo.MyLog;
import org.springframework.web.bind.annotation.*;

/**
 * @program: sc-f-chapter1
 * @description: 测试loginRequired注解
 * @author: zzs
 * @create: 2020-03-16 14:53
 **/
@RestController
@RequestMapping("/index")
public class LoginRequiredController {
    @GetMapping("/sourceA")
    public String sourceA(){
        return "你正在访问sourceA资源";
    }

    @LoginRequired
    @GetMapping("/sourceB")
    public String sourceB(){
        return "你正在访问sourceB资源";
    }

    @GetMapping("/sourceC")
    @MyLog
    public String sourceC(@RequestParam String sourceName){
        return "你正在访问sourceC资源";
    }
}
