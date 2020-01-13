package com.zhou.servicefeign.controller.hutool.str;

/**
 * 判空 拼接 支持模板类型 的字符串拼接
 */
public class StrUtil {
    public static void main(String[] args) {
        String template = "{}爱{}，就像老鼠爱大米";
        //str -> 我爱你，就像老鼠爱大米
        String str = cn.hutool.core.util.StrUtil.format(template, "老鼠", "毛");

        System.out.println(str);
    }
}
