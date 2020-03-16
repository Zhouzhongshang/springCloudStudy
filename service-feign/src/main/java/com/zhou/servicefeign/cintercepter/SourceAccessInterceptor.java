package com.zhou.servicefeign.cintercepter;

import com.zhou.servicefeign.controller.annotationdemo.LoginRequired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: sc-f-chapter1
 * @description: 自定义拦截器
 * @author: zzs
 * @create: 2020-03-16 15:05
 **/
public class SourceAccessInterceptor implements HandlerInterceptor {

    /**
     * 进入url地址之前的操作
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入拦截器了");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //用户登录时可以在这里拿到权限信息、再返回
        LoginRequired methodAnnotation = handlerMethod.getMethodAnnotation(LoginRequired.class);
        if (methodAnnotation == null) {
            //没加注解的直接放行
            return true;
        }
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().print("对不起，您访问的资源需要登录");
        //有注解的不放行，提示
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
