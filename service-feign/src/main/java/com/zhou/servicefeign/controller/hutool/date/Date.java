package com.zhou.servicefeign.controller.hutool.date;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.util.Calendar;

/**
 * 日期工具
 * DateUitl
 * DateTime
 *
 */
public class Date {
    public static void main(String[] args) {
        /**
         * 当前时间
         */

        DateTime date = DateUtil.date();
        DateTime date1 = DateUtil.date(Calendar.getInstance());
        DateTime date2 = DateUtil.date(System.currentTimeMillis());
        //当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
        String now = DateUtil.now();
        //当前日期字符串，格式：yyyy-MM-dd
        String today = DateUtil.today();
        /**
         * 同时支持字符串日期转换、时间差、格式化时间差
         * 计时器 获取代码执行的时间
         */


        TimeInterval timer = DateUtil.timer();
        BufferedInputStream inputStream = FileUtil.getInputStream("D:\\zzs\\test\\01\\01.txt");
        BufferedOutputStream outputStream = FileUtil.getOutputStream("D:\\zzs\\test\\02\\02.txt");
        long copy = IoUtil.copy(inputStream, outputStream);
        long interval = timer.interval();
        System.out.println(interval);
    }
}
