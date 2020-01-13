package com.zhou.servicefeign.controller.hutool.io;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.FileReader;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

/**
 * FileUtil
 *   FileReader 更加直观 支持1行1行
 *   FileWriter
 */
public class Io {
    public static void main(String[] args) {

        TimeInterval timer1 = DateUtil.timer();
        BufferedInputStream inputStream = FileUtil.getInputStream("D:\\zzs\\test\\01\\01.txt");
        BufferedOutputStream outputStream = FileUtil.getOutputStream("D:\\zzs\\test\\02\\02.txt");
        long copy = IoUtil.copy(inputStream, outputStream);


        FileUtil.touch("D:\\zhouzhongshan\\1.txt");
        FileUtil.touch("D:\\wangjiaojiao\\1.txt");

        FileReader fileReader = new FileReader("D:\\zzs\\test\\01\\01.txt");
        String s = fileReader.readString();

        System.out.println(s);
        long interval = timer1.interval();
        System.out.println(interval);


    }
}
