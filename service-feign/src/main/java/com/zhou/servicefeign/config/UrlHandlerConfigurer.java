package com.zhou.servicefeign.config;

import com.zhou.servicefeign.cintercepter.SourceAccessInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: sc-f-chapter1
 * @description: 自定义拦截器
 * @author: zzs
 * @create: 2020-03-16 15:01
 **/
@Configuration
public class UrlHandlerConfigurer implements WebMvcConfigurer {

    /**
     * 拦截器的注册
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SourceAccessInterceptor()).addPathPatterns("/**");
    }
}
