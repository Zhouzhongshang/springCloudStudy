package com.zhou.serviceribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 *
 * 什么是ribbon:
 *   ribbon是一个负载均衡客户端，可以很好的控制htt和tcp的一些行为。Feign默认集成了ribbon。
 *
 *
 * 业务场景：
 * 一个服务注册中心，eureka server,端口为8761
 * service-hi工程跑了两个实例，端口分别为8762,8763，分别向服务注册中心注册
 * sercvice-ribbon端口为8764,向服务注册中心注册
 * 当sercvice-ribbon通过restTemplate调用service-hi的hi接口时，因为用ribbon进行了负载均衡，
 *   会轮流的调用service-hi：8762和8763 两个端口的hi接口；
 *
 */
@SpringBootApplication
//Eureka注册中心 都是让注册中心扫描到服务
@EnableEurekaClient
//可以使其他注册中心
@EnableDiscoveryClient
//使用断路器
@EnableHystrix
public class ServiceRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRibbonApplication.class, args);
    }

    /**
     * 开启负载均衡的功能 LoadBalanced
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
