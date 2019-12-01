package com.zhou.servicehi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@SpringBootApplication
@EnableEurekaClient
@RestController  //作为页面呈现
public class ServiceHiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceHiApplication.class, args);
    }

    @Value("${server.port}")
    private String port;

    //get提交的参数为?name="XXX"
    @RequestMapping("/hi")
    public String Home(@RequestParam(value = "name",defaultValue = "zhou") String name) {
        return "hi " + name + " ,i am from port:" + port;
    }
}
