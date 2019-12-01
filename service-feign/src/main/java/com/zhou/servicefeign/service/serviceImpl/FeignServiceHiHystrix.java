package com.zhou.servicefeign.service.serviceImpl;

import com.zhou.servicefeign.service.FeignServiceHi;
import org.springframework.stereotype.Component;

/**
 * 断路器的方法返回
 */
@Component
public class FeignServiceHiHystrix implements FeignServiceHi {
    @Override
    public String sayHiFromClient(String name) {
        return "sorry:"+name;
    }
}
