package com.example.springbootdemo.utils;

import org.hibernate.validator.internal.util.StringHelper;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

import static com.google.common.base.Preconditions.*;
import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Math.abs;

/**
 * 日期时间工具类
 */
public class DateUtil {
    /**
     * 常用时间格式
     */
    public static final String Format_Date = "yyyy-MM-dd";
    public static final String Format_Time = "HH:mm:ss";

    public static final String Format_Date_Time = "yyyy-MM-dd HH:mm:ss";

    /**
     * @param yyyyMMdd
     * @return
     */
    public static final String patternB = "yyyyMMdd";

    public static final String patternC = "HHmmss";

    public static final String patternD = "yyyy-MM";

    public static final String patternE = "MM-dd HH:mm";

    public static final String patternF = "yyyyMMddHHmmss";

    public static final String patternG = "HH:mm";
    public static final long operateDays = 1426348800000l;//2015-03-15 00:00:00 时间毫秒数  平台开始的那天

    /**
     * 24小时的秒数
     */
    public static final int twenty_four_hours = 60 * 60 * 24;
    /**
     * 12小时的秒数
     */
    public static final int twelve = 60 * 60 * 12;
    /**
     * 线程本地变量，确保线程间的数据安全
     */
    public static final ThreadLocal<SimpleDateFormat> SHORT_DAY = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm");
        }
    };
    public static final ThreadLocal<SimpleDateFormat> MMDDHH = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm MM-dd");
        }
    };
    public static final ThreadLocal<SimpleDateFormat> MDHM = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("M月d日 HH:mm");
        }
    };

    public static final ThreadLocal<SimpleDateFormat> MD = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("M月d日");
        }
    };
    public static final ThreadLocal<SimpleDateFormat> YMD = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy年M月d日");
        }
    };
    public static final ThreadLocal<SimpleDateFormat> YM = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy年M月");
        }
    };

    public static final ThreadLocal<SimpleDateFormat> YMDHM = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy年M月d日 HH:mm");
        }
    };

    /**
     * 默认的日期格式化器，格式为yyyy-MM-dd
     */
    public static final ThreadLocal<SimpleDateFormat> DEFAULT_DATE_FORMATER = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(Format_Date);
        }
    };

    /**
     * 默认的日期格式化器，格式为HH:mm:ss
     */
    public static final ThreadLocal<SimpleDateFormat> DEFAULT_TIME_FORMATER = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(Format_Time);
        }
    };
    /**
     * 默认的时间格式化器，格式为yyyy-MM-dd HH:mm:ss
     */
    public static final ThreadLocal<SimpleDateFormat> DEFAULT_DATETIME_FORMATER = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };


    /**
     * 默认的时间格式化器，格式为yyyy-MM-dd HH:mm:ss
     */
    public static final ThreadLocal<SimpleDateFormat> YYYY_MM_DD_HH = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:00");
        }
    };
    /**
     * 格式是 yyyy-mm-dd 00：00：00 的格式转换器
     */
    public static final ThreadLocal<SimpleDateFormat> ZERO_HOUR_DATETIME_FORMATER = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        }
    };

    /**
     * 格式是 yyyy-mm-dd 00：00：00 的格式转换器
     */
    public static final ThreadLocal<SimpleDateFormat> HOUR_DATETIME_FORMATER = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:00");
        }
    };
    public static final ThreadLocal<SimpleDateFormat> YYYYMMDDHHMMSS = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMddHHmmss");
        }
    };

    public static final ThreadLocal<SimpleDateFormat> YYYYMMDDHHMM = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }
    };

    public static final ThreadLocal<SimpleDateFormat> YYMMDD = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyMMdd");
        }
    };

    public static final ThreadLocal<SimpleDateFormat> YYYYMM = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM");
        }
    };

    public static final ThreadLocal<SimpleDateFormat> YYYYMMDD = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

    public static final ThreadLocal<SimpleDateFormat> HHMMSSSSS = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HHmmssSSS");
        }
    };

    public static final ThreadLocal<SimpleDateFormat> HHMMSS = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HHmmss");
        }
    };

    public static final ThreadLocal<SimpleDateFormat> HHMM = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm");
        }
    };

    /**
     * 判断日期是否为同一月
     * Description: 请添加方法说明：
     *
     * @param dateA
     * @param dateB
     * @return 参数
     * @author 肖武胜
     */
    public static boolean isSameMonth(Date dateA, Date dateB) {
        if (dateA == null || dateB == null) {
            return false;
        }
        Calendar calDateA = Calendar.getInstance();
        calDateA.setTime(dateA);

        Calendar calDateB = Calendar.getInstance();
        calDateB.setTime(dateB);

        return calDateA.get(Calendar.MONTH) == calDateB.get(Calendar.MONTH);
    }

    /**
     * 判断日期是否为同一天
     *
     * @param dateA
     * @param dateB
     * @return
     * @author: MaoWu1
     * @date: 2014年4月2日下午6:11:27
     */
    public static boolean isSameDay(Date dateA, Date dateB) {
        Calendar calDateA = Calendar.getInstance();
        calDateA.setTime(dateA);

        Calendar calDateB = Calendar.getInstance();
        calDateB.setTime(dateB);

        return calDateA.get(Calendar.YEAR) == calDateB.get(Calendar.YEAR)
                && calDateA.get(Calendar.MONTH) == calDateB.get(Calendar.MONTH)
                && calDateA.get(Calendar.DAY_OF_MONTH) == calDateB
                .get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 日期加天数,可以向前加，向后加
     *
     * @param date 日期
     * @param day  天数
     * @return 返回相加后的日期
     */
    public static Date addDay(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, day);

        return c.getTime();
    }

    /**
     * 加减指定天数
     *
     * @param date
     * @param day
     * @return
     */
    public static Date addZdDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, day);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime();   //这个时间就是日期往后推一天的结果
        return date;
    }

    /**
     * 日期加分钟,可以向前加，向后加
     *
     * @param date 日期
     * @param min  分钟
     * @return 返回相加后的日期
     */
    public static Date addDate(Date date, int min) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) + (min * 60 * 1000L));

        return c.getTime();
    }

    /**
     * 日期加月,可以向前加，向后加
     *
     * @param date  日期
     * @param month 月份
     * @return 返回相加后的日期
     */
    public static Date addMonth(Date date, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, month);

        return c.getTime();
    }

    public static Date addSecond(Date date, int second) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, second);

        return c.getTime();
    }

    public static int getDifferDays(Date startData, Date endDate) {
        return (int) DateUtil.getDifferSeconds(endDate, startData) / (24 * 60 * 60);
    }

    public static void main(String[] args) {
//		String endDate = DateUtil.format(DateUtil.getDateAfter(DateUtil.parseDate("2018-4-15"),180));
//		System.out.println(endDate+"---"+compareDate("2018-04-08","2018-10-08"));
//		if(compareDate("2018-12-13","2018-12-15") < 1){
//			System.out.println("您的登录密码"+differenceDays(DateUtil.parseDate("2018-10-08"),DateUtil.parseDate(endDate))+"天后即将到期，请及时修改");
//
//		}
        System.out.println(DateUtil.getFirstTimeByDay(DateUtil.addDay(new Date(), -1)));
    }

    /**
     * Description: 请添加方法说明：获取当前时分秒，前、后多少小时的时间
     *
     * @param hour -2表示两小时前的时间，2表示两小时后的时间
     * @return 参数
     * @author: tanghua
     */
    public static Date getHourDateTimeDiffer(int hour) {
        Calendar calen = Calendar.getInstance();//可以对每个时间域单独修改
        calen.setTime(new Date());
        calen.set(Calendar.HOUR_OF_DAY, calen.get(Calendar.HOUR_OF_DAY) + (hour));
        return calen.getTime();
    }

    /**
     * 日期加月,可以向前加，向后加
     *
     * @param date  日期
     * @param month 月份
     * @return 返回相加后的日期
     */
    public static Date addYear(Date date, int year) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, year);

        return c.getTime();
    }

    /**
     * Description: 返回两个时间之间的所有月份
     *
     * @param minDate
     * @param maxDate
     * @return 返回两个时间之间的所有月份，格式为yyyy-MM
     * @author: 彭洪
     */
    public static List<String> getMonthBetween(Date minDate, Date maxDate) {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        min.setTime(minDate);
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(maxDate);
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;

    }

    /**
     * Description: 返回两个时间之间的所有月份
     *
     * @param minDate
     * @param maxDate
     * @return 返回两个时间之间的所有月份，格式为yyyyMM
     */
    public static List<String> getMonthBetweenYYYYMM(Date minDate, Date maxDate) {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");//格式化为年月
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        min.setTime(minDate);
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(maxDate);
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }
        if (result != null) {
            Collections.reverse(result);
        }
        return result;

    }

    /**
     * 获取当前时间的日期-时 数据，即将当前的分钟、秒置为0后的数值, *
     *
     * @param hour 要加减的小时数，如果为0表示当前小时
     * @return
     */
    public static Date getDayHourDate(int hour) {
        Calendar today = Calendar.getInstance();
        today.setTimeInMillis(System.currentTimeMillis());
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.add(Calendar.HOUR_OF_DAY, hour);
        return today.getTime();
    }

    /**
     * 获得某天0时0分0秒的秒数
     *
     * @param day 今天就是0，昨天就是-1，明天就是1，由此类推
     * @return
     */
    public static Date getSomeDayDate(int day) {
        // return (System.currentTimeMillis()/86400000*86400000L-(23-
        // Calendar.ZONE_OFFSET)*3600000L +
        // day*24*3600000L)/1000;
        // 上面这种方法在服务器执行发现跟系统时间并不一致，早上8点获取到的是新的一天的数据，屏蔽掉。用系统提供的方法以免出错
        Calendar today = Calendar.getInstance();
        today.setTimeInMillis(System.currentTimeMillis());
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.add(Calendar.DAY_OF_YEAR, day);
        return today.getTime();
    }

    /**
     * 获取当前日期毫秒数
     *
     * @return 返回当前日期毫秒
     */
    public static long getCurrentMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 返回日期代表的毫秒
     *
     * @param date 日期
     * @return 返回毫秒
     */
    public static long getMillis(Date date) {
        if (date == null) {
            return 0L;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    /**
     * 取得当前日期（只有日期，没有时间，或者可以说是时间为0点0分0秒）
     *
     * @return
     * @throws ParseException
     */
    public static Date getCurrentDate() throws ParseException {
        Date date = new Date();
        date = DateUtil.DEFAULT_DATE_FORMATER.get().parse(
                DateUtil.DEFAULT_DATE_FORMATER.get().format(date));//
        return date;
    }

    public static Date getToday() {
        Date date = null;
        try {
            date = getCurrentDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 取当天日期
     *
     * @param format
     * @return
     */
    public static String getCurrentDate(String format) {
        if (format == null) {
            format = Format_Date;
        }
        java.util.Date utilDate = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format);
        return sdf.format(utilDate);
    }

    /**
     * 取得当前时间（包括日期和时间）
     *
     * @return 当前时间
     */
    public static Date getCurrentDateTime() {
        return longTimeToDateTime(System.currentTimeMillis());
    }

    /**
     * 用默认的日期格式，格式化一个Date对象
     *
     * @param date 待被格式化日期
     * @return “yyyy-MM-dd”格式的日期字符串
     */
    public static String formatDate(Date date) {
        return date == null ? "" : DateUtil.DEFAULT_DATE_FORMATER.get().format(
                date);
    }

    /**
     * 用默认的日期格式，格式化一个Date对象
     *
     * @param date 待被格式化日期
     * @return “yyyy-MM-dd”格式的日期字符串
     */
    public static String formatDateWithChiness(Date date) {
        return date == null ? "" : DateUtil.YMD.get().format(
                date);
    }

    /**
     * 根据传入的格式，将日期对象格式化为日期字符串
     *
     * @return 格式后的日期字符串
     */
    public static String formatCurrent_yyyyMMdd() {
        return DateUtil.YYYYMMDD.get().format(new Date());
    }

    /**
     * 根据传入的格式，将日期对象格式化为日期字符串
     *
     * @return 格式后的日期字符串
     */
    public static String formatCurrent_HHmmssSSS() {
        return DateUtil.HHMMSSSSS.get().format(new Date());
    }

    /**
     * Description: 请添加方法说明：格式化日期 yyyyMMdd
     *
     * @param date
     * @return 参数
     * @author: zhangzc
     */
    public static String formatCurrent_yyyyMMdd(Date date) {
        return DateUtil.YYYYMMDD.get().format(date);
    }

    /**
     * 根据传入的格式，将日期对象格式化为日期字符串
     *
     * @return 格式后的日期字符串
     */
    public static String formatCurrent_HHmmss() {
        return DateUtil.HHMMSS.get().format(new Date());
    }

    /**
     * 根据传入的格式，将日期对象格式化为日期字符串
     *
     * @param date   待被格式化日期
     * @param format 自定义日期格式器
     * @return 格式后的日期字符串
     */
    public static String formatDate(Date date, String format) {
        String s = "";

        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            s = sdf.format(date);
        }

        return s;
    }

    /**
     * 用默认的日期时间格式，格式化一个Date对象
     *
     * @param date 待被格式化日期
     * @return “yyyy-MM-dd HH:mm:ss”格式的日期时间字符串
     */
    public static String formatTimeToYYYYMMDDHH(Date date) {
        return date == null ? "" : DateUtil.YYYY_MM_DD_HH.get()
                .format(date);
    }

    /**
     * 用默认的日期时间格式，格式化一个Date对象
     *
     * @param date 待被格式化日期
     * @return “yyyy-MM-dd HH:mm:ss”格式的日期时间字符串
     */
    public static String formatTime(Date date) {
        return date == null ? "" : DateUtil.DEFAULT_DATETIME_FORMATER.get()
                .format(date);
    }

    /**
     * 用默认的日期时间格式，格式化一个Date对象
     *
     * @param date 待被格式化日期
     * @return “HH:mm:ss”格式的日期时间字符串
     */
    public static String formatHourTime(Date date) {
        return date == null ? "" : DateUtil.DEFAULT_TIME_FORMATER.get()
                .format(date);
    }


    /**
     * 用默认的日期时间格式，格式化一个Date对象
     *
     * @param date 待被格式化日期
     * @return “HH:00”格式的日期时间字符串
     */
    public static String formatHour(Date date) {
        return date == null ? "" : DateUtil.HOUR_DATETIME_FORMATER.get()
                .format(date);
    }

    public static String formatZeroHourTime(Date date) {
        return date == null ? "" : DateUtil.ZERO_HOUR_DATETIME_FORMATER.get()
                .format(date);
    }

    /**
     * Description: 请添加方法说明： 用默认的日期时间格式，格式化一个Date对象
     *
     * @param date
     * @return “yyyMMddHHmmss”格式的日期时间字符串
     */
    public static String formatTimeYYYYMMDDHHmmss(Date date) {
        return date == null ? "" : DateUtil.YYYYMMDDHHMMSS.get()
                .format(date);
    }

    /**
     * 获取指定天数后的日期
     *
     * @param baseDate 基准日期
     * @param day      后推天数
     * @return 后推后的天数
     */
    public static Date getDateAfter(Date baseDate, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(baseDate);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 获取指定月份后的日期
     *
     * @param baseDate 基准日期
     * @param day      后推月数，可为负
     * @return 后推后的日期
     */
    public static Date getDateAfterMonth(Date baseDate, int month) {
        Calendar now = Calendar.getInstance();
        now.setTime(baseDate);
        now.add(Calendar.MONTH, month);
        return now.getTime();
    }

    /**
     * 获取指定分钟数后的日期
     *
     * @param baseDate 基准日期
     * @param minute   后推分钟数， 可为负
     * @return
     */
    public static Date getDateAfterMinute(Date baseDate, int minute) {
        Calendar now = Calendar.getInstance();
        now.setTime(baseDate);
        now.add(Calendar.MINUTE, minute);
        return now.getTime();
    }

    /**
     * 利用默认的格式（yyyy-MM-dd）将一个表示日期的字符串解析为日期对象
     *
     * @param dateStr 待格式化日期字符串
     * @return 格式化后日期对象
     * @throws RuntimeException
     */
    public static Date parseDate(String dateStr) {
        if (dateStr == null || dateStr.length() == 0) {
            return null;
        }
        try {
            return DateUtil.DEFAULT_DATE_FORMATER.get().parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 利用默认的格式（yyyy年MM月dd日）将一个表示日期的字符串解析为日期对象
     * Description: 请添加方法说明：
     *
     * @param dateStr
     * @return 参数
     */
    public static Date parseDateWithChiness(String dateStr) {
        if (dateStr == null || dateStr.length() == 0) {
            return null;
        }
        try {
            return DateUtil.YMD.get().parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 利用默认的格式（yyyy-MM-dd HH:mm:ss）将一个表示时间的字符串解析为日期对象
     *
     * @param timeStr 时间字符串
     * @return 格式化后的日期对象
     * @throws ParseException
     */
    public static Date parseTime(String timeStr) {
        try {
            return DateUtil.DEFAULT_DATETIME_FORMATER.get().parse(timeStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Description: 利用默认的格式（yyyy-MM-dd HH:mm）将一个表示时间的字符串解析为日期对象
     *
     * @param timeStr 时间字符串
     * @return 参数 格式化后的日期对象
     * @author: zhangzc
     */
    public static Date parseYYYYMMDDHHMM(String timeStr) {
        try {
            return DateUtil.YYYYMMDDHHMM.get().parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static Date parse_YYYYMM(String date) {
        try {
            return DateUtil.YYYYMM.get().parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static Date parse_YYYYMMDD(String date) {
        try {
            return DateUtil.YYYYMMDD.get().parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static Date parse_HHMM(String date) {
        return parseYYYYMMDDHHMM(formatDate(new Date()) + " " + date);
    }

    /**
     * Description: 请添加方法说明： 使用格式（yyyy-MM-dd 00:00:00）将一个表示时间的字符串解析为日期对象
     *
     * @param timeStr
     * @return 参数
     */
    public static Date parseZeroHourTime(String timeStr) {
        try {
            return DateUtil.ZERO_HOUR_DATETIME_FORMATER.get().parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * 将一个字符串，按照特定格式，解析为日期对象
     *
     * @param datetimeStr 日期、时间、日期时间字符串
     * @param format      自定义日期格式器
     * @return 格式化后的日期对象
     * @throws ParseException
     */
    public static Date parseDateTime(String datetimeStr, String format) {
        Date date = null;
        try {
            date = (new SimpleDateFormat(format)).parse(datetimeStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return date;
    }

    /**
     * 得到日期年份
     *
     * @return 当前年份
     */
    public static int getCurrentYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 得到日期月份
     *
     * @return 当前时间
     */
    public static int getCurrentMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 得到当前年份
     *
     * @return 当前年份
     */
    public static int getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    /**
     * 得到当前月份（1至12）
     *
     * @return 当前月份（1至12）
     */
    public static int getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 得到当前小时 （24小时制）
     *
     * @return 当前月份（0-23）
     */
    public static int getCurrentHour() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public static String getLastQuarter() {
        int year = getCurrentYear();
        int month = getCurrentMonth();
        int quarter = (month - 1) / 3 + 1;
        if (quarter != 1) {
            return year + "-" + "0" + (quarter - 1);
        } else {
            return (year - 1) + "-" + "04";
        }

    }

    /**
     * 获取yyyy-MM-dd格式的当前系统日期
     *
     * @return 当前系统日期
     */
    public static String getCurrentDateAsString() {
        return new SimpleDateFormat(DateUtil.Format_Date).format(new Date());
    }

    /**
     * 获取指定格式的当前系统日期
     *
     * @param format 自定义日期格式器
     * @return 当前系统日期
     */
    public static String getCurrentDateAsString(String format) {
        SimpleDateFormat t = new SimpleDateFormat(format);
        return t.format(new Date());
    }

    /**
     * 获取HH:mm:ss格式的当前系统时间
     *
     * @return 当前系统时间
     */
    public static String getCurrentTimeAsString() {
        return new SimpleDateFormat(DateUtil.Format_Time).format(new Date());
    }

    /**
     * 获取指定格式的当前系统时间
     *
     * @param format 自定义日期格式器
     * @return 当前系统时间
     */
    public static String getCurrentTimeAsString(String format) {
        SimpleDateFormat t = new SimpleDateFormat(format);
        return t.format(new Date());
    }

    /**
     * 获取当前为星期几,从星期日~星期六对应的值是1~7
     *
     * @return 星期几
     * @date: 2013年12月31日下午3:35:08
     */
    public static int getDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取指定日期为星期几,从星期日~星期六对应的值是1~7
     *
     * @param date 指定日期
     * @return 星期几
     * @date: 2013年12月31日下午3:45:35
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取星期几的中文名称
     *
     * @return 星期几
     */
    public static String getChineseDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        return DateUtil.getChineseDayOfWeek(cal.getTime());
    }

    /**
     * 获取星期几的中文名称
     *
     * @param date 指定日期
     * @return 星期几
     */
    public static String getChineseDayOfWeek(String date) {
        return DateUtil.getChineseDayOfWeek(DateUtil.parseDate(date));
    }

    /**
     * 获取星期几的中文名称
     *
     * @param date 指定日期
     * @return 星期几
     */
    public static String getChineseDayOfWeek(Date date) {
        int dateOfWeek = DateUtil.getDayOfWeek(date);
        if (dateOfWeek == Calendar.MONDAY) {
            return "星期一";
        } else if (dateOfWeek == Calendar.TUESDAY) {
            return "星期二";
        } else if (dateOfWeek == Calendar.WEDNESDAY) {
            return "星期三";
        } else if (dateOfWeek == Calendar.THURSDAY) {
            return "星期四";
        } else if (dateOfWeek == Calendar.FRIDAY) {
            return "星期五";
        } else if (dateOfWeek == Calendar.SATURDAY) {
            return "星期六";
        } else if (dateOfWeek == Calendar.SUNDAY) {
            return "星期日";
        }
        return null;
    }

    /**
     * 获取当天为几号
     *
     * @return 几号
     * @date: 2013年12月31日下午3:50:11
     */
    public static int getDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定日期为几号
     *
     * @param date 指定的日期
     * @return 几号
     * @date: 2013年12月31日下午3:50:40
     */
    public static int getDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定日期所在月份的最后一天是几号
     *
     * @param date 指定日期
     * @return 指定日期所在月份的最后一天是几号
     * @date: 2013年12月31日下午3:51:07
     */
    public static int getMaxDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定日期所在月份的第一天
     *
     * @param date 指定日期
     * @return 指定日期所在月份的第一天
     * @date: 2013年12月31日下午4:16:56
     */
    public static String getFirstDayOfMonth(String date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.parseDate(date));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return new SimpleDateFormat(DateUtil.Format_Date).format(cal.getTime());
    }

    /**
     * 获取指定日期所在年的第一天
     *
     * @param date 指定日期
     * @return 指定日期所在年份的第一天
     * @date: 2013年12月31日下午4:16:56
     */
    public static String getFirstDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        return new SimpleDateFormat(DateUtil.Format_Date).format(cal.getTime());
    }

    /**
     * 获取当前月最后一天
     *
     * @param date
     * @return
     */
    public static String getLastDayOfMonth(String date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.parseDate(date));
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return new SimpleDateFormat(DateUtil.Format_Date).format(cal.getTime());
    }

    /**
     * 获取当天为一年中第几天
     *
     * @return 一年中第几天
     * @date: 2013年12月31日下午4:03:57
     */
    public static int getDayOfYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取指定日期为一年中第几天
     *
     * @param date 指定日期
     * @return 一年中第几天
     * @date: 2013年12月31日下午4:04:21
     */
    public static int getDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取指定日期为星期几,从星期日~星期六对应的值是1~7
     *
     * @param date 指定日期
     * @return 星期几
     * @date: 2013年12月31日下午3:45:35
     */
    public static int getDayOfWeek(String date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.parseDate(date));
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取指定日期为几号
     *
     * @param date 指定的日期
     * @return 几号
     * @date: 2013年12月31日下午3:50:40
     */
    public static int getDayOfMonth(String date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.parseDate(date));
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定日期为一年中第几天
     *
     * @param date 指定日期
     * @return 一年中第几天
     * @date: 2013年12月31日下午4:04:21
     */
    public static int getDayOfYear(String date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.parseDate(date));
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 时间戳转换:把距离GMT时间的毫秒数转为日期，中国处在东八区，所以是：GMT时间+8小时
     *
     * @param time 距离GTM时刻的毫秒数
     * @return 获取到的北京时区日期时间字符串
     */
    public static String longTimeToDateTimeString(Long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = format.format(time);
        return d;
    }

    /**
     * 时间戳转换:把距离GMT时间的毫秒数转为日期，中国处在东八区，所以是：GMT时间+8小时
     *
     * @param time 距离GTM时刻的毫秒数
     * @return 获取到的北京时区日期时间对象
     */
    public static Date longTimeToDateTime(long time) {
        String d = DateUtil.DEFAULT_DATETIME_FORMATER.get().format(time);
        return DateUtil.parseTime(d);
    }

    /**
     * 用户界面显示
     */
    private static final long timeMin = 60;
    private static final long timeHalfHour = 60 * 30;
    private static final long timeHour = 60 * 60;
    private static final long timeDay = 60 * 60 * 24;
    private static final long time2Day = 60 * 60 * 24 * 2;

    /**
     * 1分钟内： 刚刚 半小时内： XX分钟前 今天： 今天**：**（时：分） 昨天： 昨天**：**（时：分） 前天： 前天**：**（时：分）
     * 早于前天： mm月dd日 **：**（时：分） 早于今年： yyyy年mm月dd日 **：**（时：分）
     *
     * @param displayTime
     * @param isShowHM
     * @return
     */
    public static String getDisplayTime(long displayTime, boolean isShowHM) {
        Calendar now = Calendar.getInstance();
        Calendar show = Calendar.getInstance();
        show.setTimeInMillis(displayTime);
        Date mDownloadDate = show.getTime();
        long time = (now.getTimeInMillis() - mDownloadDate.getTime()) / 1000;
        if (time < (-5) * DateUtil.timeMin) {
            return DateUtil.YMDHM.get().format(mDownloadDate);
        }
        if (time < DateUtil.timeMin) {
            return "刚刚";
        }
        if (time < DateUtil.timeHalfHour) {
            return (time / 60) + "分钟前";
        }
        long todayPassTime = now.get(Calendar.HOUR_OF_DAY) * DateUtil.timeHour
                + now.get(Calendar.MINUTE) * DateUtil.timeMin
                + now.get(Calendar.SECOND);
        if (time < todayPassTime) {
            return "今天"
                    + (isShowHM ? DateUtil.SHORT_DAY.get()
                    .format(mDownloadDate) : "");
        }
        if (time < DateUtil.timeDay + todayPassTime) {
            return "昨天"
                    + (isShowHM ? DateUtil.SHORT_DAY.get()
                    .format(mDownloadDate) : "");
        }
        if (time < DateUtil.time2Day + todayPassTime) {
            return "前天"
                    + (isShowHM ? DateUtil.SHORT_DAY.get()
                    .format(mDownloadDate) : "");
        }
        SimpleDateFormat format = (isShowHM) ? DateUtil.YMDHM.get()
                : DateUtil.YMD.get();
        if (now.get(Calendar.YEAR) == show.get(Calendar.YEAR)) {
            format = (isShowHM) ? DateUtil.MDHM.get() : DateUtil.MD.get();
        }
        return format.format(mDownloadDate);
    }

    // timeStamp,linux时间戳
    public static long getNow() {
        return System.currentTimeMillis() / 1000L;
    }

    /**
     * 清除日期对象的时间部分, 只保留日期(年月日)信息
     *
     * @param date
     * @return
     */
    public static Date clearTime(Date date) {
        return new DateTime(date).withMillisOfDay(0).toDate();
    }

    /**
     * 用来判断时间d是否在start和end之间, 如果start为空, 则表示没有起始时间限制, 同样end为空时即没有结束时间限制.
     *
     * @param start 开始时间
     * @param end   结束时间
     */
    public static boolean between(Date start, Date end, Date d) {
        checkNotNull(d);
        checkState(!(start == null && end == null),
                "start and end arguments must appear at least one ");
        // 如果没有开始时间或结束时间
        if (start == null) {
            return d.compareTo(end) <= 0;
        } else if (end == null) {
            return d.compareTo(start) >= 0;
        } else {
            return d.compareTo(start) >= 0 && d.compareTo(end) <= 0;
        }
    }

    /**
     * 判断时间是否为全天时间, 即只有date信息, 没有time信息, 比如 2014-10-10
     *
     * @return 如果该时间为全天时间, 没有time信息, 则返回true
     */
    public static boolean isAllDay(Date time) {
        checkNotNull(time);
        return new DateTime(time).getSecondOfDay() == 0;
    }

    /**
     * 判断两个时间是否相等. 这里相等的含义是: 它们的相差时间段误差在毫秒内. 比如 2011-1-1 12:23:34.234 和 2011-1-1
     * 12:23:34.962 相等
     *
     * @param t1
     * @param t2
     * @return 如果两个时间仅只是毫秒级内不同或者完全相同, 返回true
     */
    public static boolean isSameTime(Date t1, Date t2) {
        return abs(t1.getTime() - t2.getTime()) < 1000;
    }

    public static long getPeriodOfHours(Date t1, Date t2) {
        Duration d = getDuration(t1, t2);
        return d.getStandardHours();
    }

    public static long getPeriodOfDays(Date t1, Date t2) {
        Duration d = getDuration(t1, t2);
        return d.getStandardDays();
    }

    public static long getPeriodOfMinutes(Date t1, Date t2) {
        Duration d = getDuration(t1, t2);
        return d.getStandardMinutes();
    }

    private static Duration getDuration(Date t1, Date t2) {
        checkArgument(t1 != null);
        checkArgument(t2 != null);
        DateTime tt1 = new DateTime(t1.getTime());
        DateTime tt2 = new DateTime(t2.getTime());
        return new Duration(tt1, tt2);
    }

    /**
     * 获取当前月份的第一天
     */
    public static Date getCurrentMonthFirstDay() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        return calendar.getTime();
    }

    /**
     * 获取当前月份的最后一天
     *
     * @return
     */
    public static Date getCurrentMonthLastDay() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取当前自然周的第一天
     */
    public static Date getCurrentWeekFirstDay() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.WEDNESDAY, 0);
        calendar.set(Calendar.DAY_OF_WEEK, 1);

        return calendar.getTime();
    }

    /**
     * 获取当前自然周的最后一天
     *
     * @return
     */
    public static Date getCurrentWeekLastDay() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMaximum(Calendar.DAY_OF_WEEK));
        return calendar.getTime();
    }

    /**
     * 获取指定月份的第一天
     *
     * @param month
     * @return
     */
    public static Date getFirstDayByMonth(Date month) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(month);
        cale.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        return cale.getTime();
    }

    /**
     * 获取指定月份的最后一天
     *
     * @param month
     * @return
     */
    public static Date getLastDayByMonth(Date month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(month);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取指定日期的开始时间<br/>
     * 例如: 2015-03-23 00:00:00
     *
     * @param day
     * @return
     */
    public static Date getFirstTimeByDay(Date day) {
        if (day == null)
            return null;
        String data = formatDate(day, Format_Date);
        return parseTime(data + " 00:00:00");
    }

    /**
     * 获取指定日期的结束时间<br/>
     * 例如： 2015-03-23 23:59:59
     *
     * @param day
     * @return
     */
    public static Date getLastTimeByDay(Date day) {
        if (day == null)
            return null;
        String data = formatDate(day, Format_Date);
        return parseTime(data + " 23:59:59");
    }

    /**
     * 获取指定日期的结束时间<br/>
     * 例如： 2015-03-23 23:59:59
     *
     * @param day
     * @return
     */
    public static Date getLastTimeByDay(Calendar day) {
        if (day == null)
            return null;
        day.set(Calendar.HOUR_OF_DAY, 23);
        day.set(Calendar.MINUTE, 59);
        day.set(Calendar.SECOND, 59);
        return day.getTime();
    }

    /**
     * 当前时间是否在给定范围之内
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return
     */
    public static boolean isCurrentDateRange(Date start, Date end) {
        if (start == null || end == null)
            return false;
        long t = new Date().getTime();
        return start.getTime() <= t && t <= end.getTime();
    }

    /**
     * 相差 天数，精确到毫秒
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return
     */
    public static int differenceDays(Date start, Date end) {
        if (start == null || end == null)
            return 0;
        long t = end.getTime() - start.getTime();
        return (int) (t / 86400000) + ((t % 86400000 == 0) ? 0 : 1);
    }

    /**
     * 相差 天数，精确到毫秒
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return
     */
    public static int differenceDays(Date start, Date end, boolean isAccurate) {
        try {
            if (isAccurate) {
                return differenceDays(start, end);
            } else {
                return differenceDays(parseZeroHourTime(formatZeroHourTime(start)), parseZeroHourTime(formatZeroHourTime(end)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String format(Date date) {
        return format(date, Format_Date);
    }

    public static String format(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        if (StringHelper.isNullOrEmptyString(pattern)) {
            pattern = Format_Date;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.format(date);
        } catch (IllegalArgumentException e) {
        }
        return "";
    }

    public static Date parse(String value, String pattern) {
        if (StringHelper.isNullOrEmptyString(value)) {
            return null;
        }
        if (StringHelper.isNullOrEmptyString(pattern)) {
            pattern = Format_Date;
        }
        SimpleDateFormat fmt = new SimpleDateFormat(pattern);
        try {
            return fmt.parse(value);
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * @param birthday    生日
     * @param compareTime 比较日期
     * @return 年龄
     * @author 肖武胜
     * Description: 获取年龄
     */
    public static int getAge(Date birthday, Date compareTime) {
        int age = 0;
        Calendar born = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        if (birthday != null) {
            now.setTime(compareTime);
            born.setTime(birthday);
            if (born.after(now)) {
                throw new IllegalArgumentException(
                        "Can't be born in the future");
            }
            age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
            if (now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR)) {
                age -= 1;
            }
        }
        return age;
    }

    /**
     * Description: 请添加方法说明： 获得当前时间的前一个月
     *
     * @param birthday
     * @param compareTime
     * @return 参数
     */
    public static Date getProMonth(Date curr, int dist) {
        Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
        ca.setTime(curr); // 设置时间为当前时间
        ca.add(Calendar.MONTH, -dist);// 月份减 dist
        Date resultDate = ca.getTime(); // 结果
        return resultDate;
    }

    /**
     * Description: 请添加方法说明： 获得当前时间的前一个天
     *
     * @param birthday
     * @param compareTime
     * @return 参数
     */
    public static Date getProDay(Date curr, int dist) {
        Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
        ca.setTime(curr); // 设置时间为当前时间
        ca.add(Calendar.DATE, -dist);// 月份减 dist
        Date resultDate = ca.getTime(); // 结果
        return resultDate;
    }

    /**
     * 获取当前时间的前一个小时
     * Description: 请添加方法说明：
     *
     * @param curr
     * @param dist
     * @return 参数
     */
    public static Date getProHour(Date curr, int dist) {
        Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
        ca.setTime(curr); // 设置时间为当前时间
        ca.add(Calendar.HOUR, -1);// 月份减 dist
        Date resultDate = ca.getTime(); // 结果
        return resultDate;
    }
	/*public static void main(String [] args) throws ParseException{
		//public static void main(String[] args){
		//	System.out.println(JSONObject.fromObject(null));
		//}
		Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
		ca.setTime(new Date()); // 设置时间为当前时间
		ca.add(Calendar.MONTH, -12);// 月份减 dist
		Date resultDate = ca.getTime(); // 结果
			System.out.println(getMonthBetweenYYYYMM(resultDate,new Date()));
		//System.out.println( DateUtil.getProMonth(new Date(),11));
	}*/

    public static final String[] zodiacArr = {"猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊"};

    public static final String[] constellationArr = {"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"};
    public static final String[] constellationArrStr = {"独立冷静水瓶座", "浪漫主义双鱼座", "善良直率白羊座", "热爱理财金牛座", "聪颖博学双子座", "温柔顾家巨蟹座", "王者风范狮子座", "完美主义处女座", "优雅迷人天秤座", "足智多谋天蝎座", "大胆冒险射手座", "成熟稳重摩羯座",};

    public static final int[] constellationEdgeDay = {20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22};

    /**
     * 根据日期获取生肖
     *
     * @return
     */
    public static String getZodica(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return zodiacArr[cal.get(Calendar.YEAR) % 12];
    }

    /**
     * 根据日期获取星座
     *
     * @return
     */
    public static String getConstellation(Date date) {
        if (date == null) {
            return "";
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (day < constellationEdgeDay[month]) {
            month = month - 1;
        }
        if (month >= 0) {
            return constellationArr[month];
        }
        // default to return 魔羯
        return constellationArr[11];
    }

    /**
     * 根据日期获取星座
     *
     * @return
     */
    public static String getConstellationStr(Date date) {
        if (date == null) {
            return "";
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (day < constellationEdgeDay[month]) {
            month = month - 1;
        }
        if (month >= 0) {
            return constellationArrStr[month];
        }
        // default to return 魔羯
        return constellationArrStr[11];
    }

    /**
     * @param sDate1 日期
     * @return 参数
     * @author 肖武胜
     * Description: 获取日期最后一天23:59:59
     */
    public static Date getLastDayOfMonth(Date sDate1) {
        Calendar cDay1 = Calendar.getInstance();
        cDay1.setTime(sDate1);
        final int lastDay = cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);
        cDay1.set(Calendar.DAY_OF_MONTH, lastDay);
        getLastTimeByDay(cDay1);
        return cDay1.getTime();
    }

    /**
     * 判断当前时间是否在活动时间内
     */
    public static Boolean checkInActivityTime(String startTimeStr, String endTimeStr) {
        Date curTime = new Date();
        Date startTime = DateUtil.parseTime(startTimeStr);
        Date endTime = DateUtil.parseTime(endTimeStr);
        return DateUtil.between(startTime, endTime, curTime);
    }

    /**
     * 判断当前时间是否在活动时间内(判断HH:mm)
     */
    public static Boolean checkInActivityTimePatternG(String startTimeStr, String endTimeStr) {
        Date curTime = new Date();
        Date startTime = DateUtil.parse_HHMM(startTimeStr);
        Date endTime = DateUtil.parse_HHMM(endTimeStr);
        return DateUtil.between(startTime, endTime, curTime);
    }

    /**
     * 返回 date1-date2相差多少秒
     * Description: 请添加方法说明：
     *
     * @param end
     * @param start
     * @return 参数
     * @author 肖武胜
     */
    public static long getDifferSeconds(Date end, Date start) {
        long result = 0;

        if (end == null || start == null)
            return result;

        result = (end.getTime() - start.getTime()) / 1000;
        if (result < 0) {
            result = 0;
        }
        return result;
    }

    /**
     * 返回 date1-date2相差多少个小时
     *
     * @param date1
     * @param Date2
     * @return
     */
//    public static int getDifferHours(Date date1, Date Date2) {
//        Long beginL = date1.getTime();
//        Long endL = Date2.getTime();
//        long day = 60 * 60 * 1000;
//        long diff = beginL - endL;
//        long hour = diff / day + (diff % day > 0 ? 1 : 0);
//        return IntegerParser.parse(hour);
//    }

    /**
     * Description: 请添加方法说明： 获得date2-date1 相差几个月(绝对值)
     *
     * @param date1
     * @param Date2
     * @return 参数
     */
    public static int getDifferMonths(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        try {
            bef.setTime(sdf.parse(date1));
            aft.setTime(sdf.parse(date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
        return Math.abs(month + result);
    }

    /**
     * Description:  获得date2-date1 相差几个月
     *
     * @param date1
     * @param date2
     * @return 参数
     */
    public static int getDifferMonthsOriginal(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        try {
            bef.setTime(sdf.parse(date1));
            aft.setTime(sdf.parse(date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
        return month + result;
    }

    /**
     * Description: 请添加方法说明： 根据年月获取日期的最后一天的日期
     *
     * @param year
     * @param month
     * @return 参数
     */

    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());

        return lastDayOfMonth;
    }

    /**
     * 获取某一天的最后时刻
     *
     * @param dateStr
     * @return
     */
    public static Date getLastTimeOfDay(String dateStr) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar.getTime();
    }

    /**
     * 获取某一天的最早时刻
     *
     * @param dateStr
     * @return
     */
    public static Date getFirstTimeOfDay(String dateStr) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        return date;

    }


    /**
     * Description: 请添加方法说明： String 转 Timestamp
     *
     * @param value
     * @return 参数
     */
    public static Timestamp parse(String value) {
        return parseTimestamp(value, "yyyy-MM-dd HH:mm:ss");
    }

    public static Timestamp parseTimestamp(String value, String pattern) {
        if (value == null || value.equals("")) {
            return null;
        }
        if ((pattern == null || pattern.equals("")) || (value.length() == 19))
            pattern = "yyyy-MM-dd HH:mm:ss";
        else if (value.length() == 16)
            pattern = "yyyy-MM-dd HH:mm";
        else if (value.length() == 13)
            pattern = "yyyy-MM-dd HH";
        else if (value.length() == 10) {
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat fmt = new SimpleDateFormat(pattern);
        try {
            return new Timestamp(fmt.parse(value).getTime());
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * 数值转换
     */
    protected static final DecimalFormat AMOUNT_SPLIT_FORMAT = new DecimalFormat(
            "#,##,##0.00");

    /**
     * Description: 请添加方法说明： 数值转换
     *
     * @param number
     * @return 参数
     */
    public static String formatAmount(BigDecimal number) {
        if (number == null) {
            return "";
        }
        return AMOUNT_SPLIT_FORMAT.format(number);
    }

    /**
     * Description: 请添加方法说明：校验是否是时间
     *
     * @param timeStr
     * @return 参数
     * @author: zhangzc
     */
    public static boolean isDateTime(String timeStr) {
        if (StringHelper.isNullOrEmptyString(timeStr)) {
            return false;
        } else {
            if (timeStr.length() == 10) {
                return false;
            } else {
                return true;
            }
        }
    }


    public static Date addHour(Date date, int hour) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, hour);

        return c.getTime();
    }


    /**
     * 获取日期list中距离日期d最近的时间
     *
     * @param list
     * @param d
     * @return
     */
    public static Date nextDay(List<Date> list, Date d) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        long gap = Long.MAX_VALUE;
        Date r = null;
        long time = d.getTime();
        for (Date t : list) {
            long tm = Math.abs(time - t.getTime());
            if (gap > tm) {
                gap = tm;
                r = t;
            }
        }
        return r;
    }

    public final static ReentrantLock lock = new ReentrantLock();

    /**
     * 将天转换为 几个月几天   从起息日开始 不包括起息日
     *
     * @param 起息日firstInterestDate
     * @param 出借期限term
     * @return 几个月几天
     */
    public static Map<String, Integer> transToMonthAndDay(String firstInterestDate, Integer term) {
        if (lock.tryLock()) {
            Map<String, Integer> resultMap = new HashMap<String, Integer>();
            try {
                Date firstDate = DateUtil.parseDate(firstInterestDate);//起息日  date
                Date endDate = DateUtil.addDay(firstDate, term);//结息日 date
                Calendar first = Calendar.getInstance();
                Calendar end = Calendar.getInstance();
                first.setTime(firstDate);
                end.setTime(endDate);
                int firstYear = first.get(Calendar.YEAR);//起息年
                int endYear = end.get(Calendar.YEAR);//结息年
                int firstMonth = first.get(Calendar.MONTH) + 1;//起息月
                int endMonth = end.get(Calendar.MONTH) + 1;//结息月
                int firstdayOfMonth = DateUtil.getDayOfMonth(firstDate);//起息天数
                int enddayOfMonth = DateUtil.getDayOfMonth(endDate);//结息天数
                if (endYear > firstYear) {//跨年
                    int result = 12 - firstMonth + endMonth;//间隔月数
                    resultMap = toMonthAndDay(firstdayOfMonth, enddayOfMonth, first, result);
                    return resultMap;
                } else {
                    int result = endMonth - firstMonth;//间隔月数
                    resultMap = toMonthAndDay(firstdayOfMonth, enddayOfMonth, first, result);
                    return resultMap;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        return null;

    }

    private static Map<String, Integer> toMonthAndDay(int firstdayOfMonth, int enddayOfMonth, Calendar first, int result) {
        Map<String, Integer> resultMap = new HashMap<String, Integer>();
        if (enddayOfMonth > firstdayOfMonth) {
            //如2016-07-07
            //2016-10-08
            if (result > 0) {
                resultMap.put("MONTH", result);
                resultMap.put("DAY", enddayOfMonth - firstdayOfMonth);
            } else {
                resultMap.put("MONTH", 0);
                resultMap.put("DAY", enddayOfMonth - firstdayOfMonth);
            }
            return resultMap;
        } else if (enddayOfMonth < firstdayOfMonth) {
            //如2016-07-07
            //2016-10-05
            first.add(Calendar.MONTH, (result - 1) > 0 ? (result - 1) : 0);//只增加2个月  至2016-09-07
            Date afterfirstDate = first.getTime();//获取到2016-09-07

            int afterenddayOfMonth = DateUtil.getDayOfMonth(afterfirstDate);//获取07
            int maxDayOfMonth = DateUtil.getMaxDayOfMonth(afterfirstDate);//获取当月最大天数
            int leftDay = maxDayOfMonth - afterenddayOfMonth;
            int finnalbeDay = leftDay + enddayOfMonth;
            if (result > 1) {
                resultMap.put("MONTH", result - 1);
                resultMap.put("DAY", finnalbeDay);
            } else {
                resultMap.put("MONTH", 0);
                resultMap.put("DAY", finnalbeDay);
            }
            return resultMap;
        } else if (enddayOfMonth == firstdayOfMonth) {
            resultMap.put("MONTH", result);
            resultMap.put("DAY", 0);
            return resultMap;
        }
        return null;
    }

    public static long getOperateDays() {//返回平台运营天数   毫秒级别的
        return System.currentTimeMillis() - operateDays;
    }

    /**
     * Description: 请添加方法说明： 如果日期是等于今天，返回今天，等于明天返回明天，其它返回日期的月日，例如 6月6日
     *
     * @param date
     * @return 参数
     * @author: tanghua
     */
    public static String getMonthDays(Date date) {
        if (date == null) {
            return null;
        }
        if (DateUtil.format(date).equals(DateUtil.format(DateUtil.getToday()))) {
            return "今天";
        } else if (DateUtil.format(date).equals(DateUtil.format(getSomeDayDate(1)))) {
            return "明天";
        }
        return DateUtil.MD.get().format(date);
    }

    /**
     * Description: 由出生日期获得年龄
     *
     * @param birthDay
     * @return
     * @throws Exception 参数
     */
    public static int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            } else {
                age--;
            }
        }
        return age;
    }

    /**
     * 获取某年第一天
     *
     * @param year
     * @return
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获得某年的最后一秒
     *
     * @param year
     * @return
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        calendar.roll(Calendar.HOUR_OF_DAY, -1);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    /**
     * 获得某年年中最后一秒 即6月30日23时59分59秒
     *
     * @param year
     * @return
     */
    public static Date getMidDate(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, 5);
        calendar.set(Calendar.DAY_OF_MONTH, 30);
        calendar.roll(Calendar.HOUR_OF_DAY, -1);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date currMidYear = calendar.getTime();

        return currMidYear;
    }

    /**
     * Description: 请添加方法说明： 比较两个日期格式 的字符，默认格式yyyy-MM-dd
     *
     * @param DATE1
     * @param DATE2
     * @return 参数 dt1>dt2前，返回1,dt1<dt2后,返回-1，如果相等等0
     * @author: tanghua
     */
    public static int compareDate(String DATE1, String DATE2) {
        try {
            Date dt1 = parseDate(DATE1);
            Date dt2 = parseDate(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * @param date1
     * @param date2
     * @return
     * @description 比较两个String类型timestamp的大小
     * @author shenwang
     */
    public static int compareDateTime(String date1, String date2) {
        if (date1 == null || date2 == null || date1.equals("") || date2.equals("")) {
            throw new IllegalArgumentException(
                    "The date cant't null and ''");
        }
        Date compareDate1 = parseTime(date1);
        Date compareDate2 = parseTime(date2);
        if (compareDate1.getTime() > compareDate2.getTime()) {
            return 1;
        } else if (compareDate1.getTime() == compareDate2.getTime()) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 比较两个日期之间相差的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getDiffDateHour(String date1, String date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException(
                    "The date cant't null");
        }
        Date compareDate1 = parseTime(date1);
        Date compareDate2 = parseTime(date2);
        long datetime1 = compareDate1.getTime();
        long datetime2 = compareDate2.getTime();
        long hour = Math.abs(datetime1 - datetime2) / (60 * 60 * 1000);
        return hour;
    }


    /**
     * 日期加秒,可以向前加，向后加
     *
     * @param date
     * @param seconds
     * @return
     */
    public static Date addSeconds(Date date, int seconds) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) + (seconds * 1000L));

        return c.getTime();
    }

    public static String getLastDayOfMonth(String year, String month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        //设置月份
        cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String lastDayOfMonth = sdf.format(cal.getTime());

        return lastDayOfMonth;
    }
}
