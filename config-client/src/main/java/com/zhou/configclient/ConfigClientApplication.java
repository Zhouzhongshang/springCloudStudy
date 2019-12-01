package com.zhou.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    /**
     * 一个服务如何从配置中心读取文件，
     *    client---->server 拿到值
     */
    @Value("${foo}")
    String foo ;

    @Value("${democonfigclient.message}")
    String message;

    @RequestMapping("/hi")
    public String getFoo() {
        System.out.println("foo:"+foo+";mes:"+message);
        return message;
    }
}
