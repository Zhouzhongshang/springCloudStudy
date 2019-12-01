package com.zhou.servicefeign.controller;

import com.zhou.servicefeign.service.FeignServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class FeignControllerHi {
    @Autowired
    private FeignServiceHi feignServiceHi;

    @RequestMapping("/hi")
    @ResponseBody
    public String sayHiFromClient(@RequestParam( value = "name" ,defaultValue = "周中山") String name){
        return feignServiceHi.sayHiFromClient(name);
    }

}
