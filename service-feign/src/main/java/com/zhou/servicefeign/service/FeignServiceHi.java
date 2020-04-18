package com.zhou.servicefeign.service;

import com.zhou.servicefeign.service.serviceImpl.FeignServiceHiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 1,指定调用的哪个服务的 哪个API 请求的HTTP的方式
 *      同理可以改造成post 和面向对象的传输
 * 2,断路器的用法
 * 3，可以负载均衡
 *
 *
 * 指定服务名、接口名、定义断路实现类来实现断路器的功能
 */
@FeignClient(value = "service-hi",fallback = FeignServiceHiHystrix.class )
public interface FeignServiceHi {

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClient(@RequestParam(value = "name" ) String name);

}
