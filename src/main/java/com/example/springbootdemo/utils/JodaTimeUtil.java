package com.example.springbootdemo.utils;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author qin
 * @description
 * @date 2020/3/9
 */
public class JodaTimeUtil {
    /**
     * @Description: 计算2个日期之间相差的  相差多少年月日   *比如：2011-02-02 到  2017-03-02 相差 6年，1个月，0天
     * @Param:
     * @return:
     * @Author: qin
     * @Date: 2020/2/27
     */
    public static String getDiffYMD(Date start, Date end) {
        StringBuilder stringBuilder = new StringBuilder();
        Interval interval = new Interval(start.getTime(), end.getTime()); //d1,d2为Date类型
        Period p = interval.toPeriod(PeriodType.yearMonthDay()); //得到相差的时间段
        return stringBuilder.append(p.getYears()).append("年").append(p.getMonths()).append("月").append(p.getDays()).append("日").toString();
    }

    /**
     * Joda-Time 计算两个时间差（年，月，星期，日，小时，分钟，秒，毫秒）   注： 开始时间 和 结束时间 格式须相同
     * @param startDateTime     开始时间
     * @param endDateTime       结束时间
     * @param dateTimeType      时间格式（2018年01月20日 21:02:37（yyyy年MM月dd日 HH:mm:ss））
     */
    public static void calculateTimeDifference(String startDateTime, String endDateTime, String dateTimeType) {

        DateTimeFormatter format = DateTimeFormat.forPattern(dateTimeType);
        DateTime dateTimeStart = format.parseDateTime(startDateTime);
        DateTime dateTimeEnd = format.parseDateTime(endDateTime);

        if (dateTimeStart.isAfter(dateTimeEnd)) {
            DateTime temp = dateTimeStart;
            dateTimeStart = dateTimeEnd;
            dateTimeEnd = temp;
        }

        Interval interval = new Interval(dateTimeStart.getMillis(), dateTimeEnd.getMillis());
        Period p = interval.toPeriod(PeriodType.yearMonthDay());
        System.out.println(p.toString());
        System.out.printf("两个日期的时间差：%d 年 %d 个月 %d 星期 %d 天 %d 小时 %d 分钟 %d 秒 %d 毫秒\n",
                p.getYears(), p.getMonths(), p.getWeeks(), p.getDays(), p.getHours(), p.getMinutes(), p.getSeconds(), p.getMillis());

    }


    public static void main(String[] args) {
//        calculateTimeDifference("2018年01月20日 21:02:37", "2019年03月01日 13:24:46", "yyyy年MM月dd日 HH:mm:ss");
//        calculateTimeDifference("2018年01月27日", "2019年03月07日", "yyyy年MM月dd日");
//        calculateTimeDifference("2019年03月01日", "2018年01月20日", "yyyy年MM月dd日");


        String dateStart = "2013-08-13";
        String dateStop = "2013-08-25";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Period period = new Period(d1.getTime(), d2.getTime());

        Date date = DateUtil.addDay(new Date(),9);
        System.out.println(period.getDays());

    }
}
