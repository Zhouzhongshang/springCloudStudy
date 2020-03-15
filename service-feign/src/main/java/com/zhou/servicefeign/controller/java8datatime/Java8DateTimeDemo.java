package com.zhou.servicefeign.controller.java8datatime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.function.Consumer;

/**
 * @program: sc-f-chapter1
 * @description: java8日期处理
 * @author: zzs
 * @create: 2020-03-15 11:46
 **/
public class Java8DateTimeDemo {
    public static void main(String[] args) throws ParseException {
        Consumer consumer = System.out::println;

        /**
         * 日期
         */
        //本地日期
        LocalDate now = LocalDate.now();
        consumer.accept(now);
        consumer.accept("年" + now.getYear() + "\n月" + now.getMonthValue() + "\n日" + now.getDayOfMonth());

        LocalDate localDate = now.plusWeeks(1);
        LocalDate plusYear = now.plus(2, ChronoUnit.YEARS);
        consumer.accept("增加一周后的日期：" + localDate);
        consumer.accept("增加两年的日期：" + plusYear);
        //初始化日期
        LocalDate of = LocalDate.of(2020, 3, 15);
        consumer.accept(of);

        //比较日期是否相等
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.of(2018, 2, 5);
        if (date1.equals(date2)) {
            consumer.accept("同");
        } else {
            consumer.accept("不同");
        }
        //比较日期的前后顺序
        if (date1.isAfter(date2)) {
            consumer.accept("当前日期大于给定日期date2");
        } else {
        }
        //计算两个时间的天数
        Period between = Period.between(date2, date1);
        consumer.accept("相差：" + between.getYears() + "年" + between.getMonths() + "月" + between.getDays() + "日");

        //年月检查信用卡之类的
        YearMonth yearMonth = YearMonth.of(2020, 2);
        int i = yearMonth.lengthOfMonth();
        consumer.accept("" + yearMonth + "有" + i + "天");

        //月和日检查生日时间
        MonthDay birthDay = MonthDay.of(now.getMonth(), now.getDayOfMonth());
        MonthDay monthDay = MonthDay.from(LocalDate.of(2021, 3, 15));
        if (monthDay.equals(birthDay)) {
            consumer.accept("是你的生日");
        }


        /**
         * 时间
         */
        LocalTime time = LocalTime.now();
        consumer.accept("当前时间" + time);

        LocalTime time1 = time.plusHours(3);
        LocalTime minusHours = time.minus(3, ChronoUnit.HOURS);
        consumer.accept("三小时后的时间为：" + time1);
        consumer.accept("3小时候之前的时间" + minusHours);


        /**
         * 时间戳
         */
        Clock clock = Clock.systemUTC();
        consumer.accept("Clock : " + clock.millis());

        Clock defaultClock = Clock.systemDefaultZone();
        consumer.accept("Clock : " + defaultClock.millis());

        Instant instant = Instant.now();
        consumer.accept(instant.toEpochMilli());


        /**
         * java8pre:转换
         */
        //Date-String  String -Date
        Date from = Date.from(instant);
        consumer.accept("Date" + from);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = simpleDateFormat.format(from);
        consumer.accept("format1:" + format1);
        Date parse1 = simpleDateFormat.parse(format1);
        consumer.accept("parse1:" + parse1);

        //相互转换 Date-Instant
        Instant instant1 = from.toInstant();
        consumer.accept("instant1:" + instant1);

        /**
         * 格式化
         */
        String dayAfterTommorrow = "20180205";
        LocalDate formatted = LocalDate.parse(dayAfterTommorrow,
                DateTimeFormatter.BASIC_ISO_DATE);
        consumer.accept(formatted);


        /**
         * java8转换
         */
        //instant-string
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());
        String format2 = dateTimeFormatter.format(instant);
        consumer.accept("format2" + format2);

        //localDateTime-String
        String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        consumer.accept("字符串时间" + format);

        //String -localDate
        LocalDateTime parse = LocalDateTime.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        consumer.accept("时间格式时间" + parse);

    }
}
