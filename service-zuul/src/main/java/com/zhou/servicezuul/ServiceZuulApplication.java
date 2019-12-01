package com.zhou.servicezuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/**
 * 开启zuul功能：
 *    ：Zuul的主要功能是路由转发和过滤器。
 *    路由功能是微服务的一部分，比如／api/user转发到到user服务，
 *    /api/shop转发到到shop服务。zuul默认和Ribbon结合实现了负载均衡的功能。
 *    地址：https://www.fangzhipeng.com/springcloud/2018/08/05/sc-f5-zuul.html
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableZuulProxy
public class ServiceZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceZuulApplication.class, args);
    }


}
