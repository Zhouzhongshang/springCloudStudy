package com.zhou.serviceribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 模拟get请求
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "sayError")
    public String hiService(String name){
        return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
    }

    /**
     * 断路器的回调，如果提供者失败了，任然可以
     * @param name
     * @return
     */
    public String sayError(String name){
        return "hi"+name+",sorry Error";
    }
}
