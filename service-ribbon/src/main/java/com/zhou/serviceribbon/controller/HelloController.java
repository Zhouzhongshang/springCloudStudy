package com.zhou.serviceribbon.controller;

import com.zhou.serviceribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

    @ResponseBody
    @RequestMapping("/hi")
    public String hi( @RequestParam(value = "name" ,defaultValue = "zhouzhongshan") String name ){
        return  helloService.hiService(name);
    }
}
