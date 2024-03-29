package com.sunwuo.electronic_mall.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author sunwuo
 * @version 2.0.0 by acy 170906
 * <p>
 * 统一的时间传入传出为String传入格式为"2017-01-01 00:00:00"
 * 添加格式时请添加一个私有的SimpleDateFormat和一个共有的静态变量并修改formatDate方法
 */

public class TimeUtil {

    private static SimpleDateFormat sdfLastHaveDay = new SimpleDateFormat("yyyy-MM-dd");

    private static SimpleDateFormat sdfCommon = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static SimpleDateFormat sdfWithoutInterval = new SimpleDateFormat("yyyyMMddHHmmss");

    private static SimpleDateFormat sdfMillisecond = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

    private static SimpleDateFormat sdfWithoutIntervalMillisecond = new SimpleDateFormat("yyyyMMddHHmmssS");

    //精确到秒
    public static final int TO_SEC = 1;
    //精确到毫秒
    public static final int TO_MS = 2;
    //精确到天
    public static final int TO_DAY = 3;
    //精确到秒无符号
    public static final int TO_SEC_NO_SYMBOL = 4;
    //精确到毫秒无符号
    public static final int TO_MS_NO_SYMBOL = 5;

    /**
     * 内部私有方法
     *
     * @param type 类型
     * @param date 需要格式的时间
     * @return 返回格式后的String
     */
    public static String formatDate(int type, Date date) {
        switch (type) {
            case TO_SEC:
                return sdfCommon.format(date);
            case TO_MS:
                return sdfWithoutInterval.format(date);
            case TO_DAY:
                return sdfLastHaveDay.format(date);
            case TO_SEC_NO_SYMBOL:
                return sdfMillisecond.format(date);
            case TO_MS_NO_SYMBOL:
                return sdfWithoutIntervalMillisecond.format(date);
            default:
                return sdfCommon.format(date);
        }
    }

    /**
     * 获取当前时间
     *
     * @param type TimeUtil的静态变量 详情见的注释
     */
    public static String getDateTime(int type) {
        Date date = new Date();
        return formatDate(type, date);

    }

    /**
     * 获取当天的开始结束时间
     */
    public static String[] getTodayDateTime() {
        String[] dateTimes = new String[2];
        Date date = new Date();
        String temp = formatDate(3, date);
        dateTimes[0] = temp + " 00:00:00";
        dateTimes[1] = temp + " 23:59:59";
        System.out.println("当天的开始时间:" + dateTimes[0]);
        System.out.println("当天的结束时间:" + dateTimes[1]);
        return dateTimes;
    }

    /**
     * 获取的昨天的开始结束时间
     */
    public static String[] getYesterdayDateTime() {
        String[] dateTimes = new String[2];
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, -1);
        String temp = formatDate(3, calendar.getTime());
        dateTimes[0] = temp + " 00:00:00";
        dateTimes[1] = temp + " 23:59:59";
        return dateTimes;
    }

    /**
     * 获取上周的开始结束时间
     */
    public static String[] getLastWeekTime() {
        System.out.println("获取上周的开始结束时间");
        String[] dateTimes = new String[2];
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
        int offset1 = 0 - dayOfWeek;
        int offset2 = 6 - dayOfWeek;
        calendar1.add(Calendar.DATE, offset1 - 7);
        calendar2.add(Calendar.DATE, offset2 - 7);
        dateTimes[0] = formatDate(3, calendar1.getTime());
        dateTimes[1] = formatDate(3, calendar2.getTime());
        return dateTimes;
    }

    /**
     * 获取的之前之后的日期
     *
     * @param dayCount 需要计算的天数 之前为- 之后为+
     * @param type     TimeUtil的静态变量 详情见的注释
     */
    public static String getBeforeAfterDay(int dayCount, int type) {
        System.out.println("获取的之前之后的日期");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, +dayCount);
        return formatDate(type, calendar.getTime());
    }

    /**
     * 获取当周的开始结束时间
     */
    public static String[] getThisWeek() {
        System.out.println("获取当周的开始结束时间");
        String[] dateTimes = new String[2];
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, 1);
        dateTimes[0] = formatDate(3, cal.getTime());
        cal.set(Calendar.DAY_OF_WEEK, 7);
        dateTimes[1] = formatDate(3, cal.getTime());
        return dateTimes;
    }

    /**
     * 获取上月的开始结束时间
     */
    public static String[] getLastMonthDateTime() {
        System.out.println("获取当周的开始结束时间");
        String[] dateTimes = new String[2];
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        dateTimes[0] = formatDate(3, calendar.getTime());
        calendar.add(Calendar.MONTH, 1);
        dateTimes[1] = formatDate(3, calendar.getTime());
        return dateTimes;
    }

    /**
     * 对时间添加减少月份
     *
     * @param dateTime 需要修改的时间
     * @param sumMonth 需要增加减少的月数
     * @param type     需要得到的格式 TimeUtil的静态变量 详情见的注释
     * @return 返回修改后的时间
     */
    public static String getDateAddMouth(String dateTime, Integer sumMonth, int type) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdfCommon.parse(dateTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.MONTH, +sumMonth);
        return formatDate(type, calendar.getTime());
    }

    /**
     * 对时间添加减少分钟
     *
     * @param dateTime       需要修改的时间
     * @param addMinuteCount 需要增加减少的分钟
     * @param type           需要得到的格式 TimeUtil的静态变量 详情见的注释
     * @return 返回修改后的时间
     */
    public static String getDateAddMinute(String dateTime, int addMinuteCount, int type) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdfCommon.parse(dateTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.MINUTE, +addMinuteCount);
        return formatDate(type, calendar.getTime());
    }

    /**
     * 对时间添加减少小时
     *
     * @param dateTime     需要修改的时间
     * @param addHourCount 需要增加减少的小时
     * @param type         需要得到的格式 TimeUtil的静态变量 详情见的注释
     * @return 返回修改后的时间
     */
    public static String getDateAddHour(String dateTime, int addHourCount, int type) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdfCommon.parse(dateTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.HOUR, +addHourCount);
        return formatDate(type, calendar.getTime());
    }

    /**
     * 对时间添加减少年数
     *
     * @param dateTime     需要修改的时间
     * @param addYearCount 需要增加减少的年数
     * @param type         需要得到的格式 TimeUtil的静态变量 详情见的注释
     * @return 返回修改后的时间
     */
    public static String getDateAddYear(String dateTime, int addYearCount, int type) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdfCommon.parse(dateTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.YEAR, +addYearCount);
        return formatDate(type, calendar.getTime());
    }

    /**
     * 对时间添加减少周数
     *
     * @param dateTime     需要修改的时间
     * @param addWeekCount 需要增加减少的周数
     * @param type         需要得到的格式 TimeUtil的静态变量 详情见的注释
     * @return 返回修改后的时间
     */
    public static String getDateAddWeek(String dateTime, int addWeekCount, int type) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdfCommon.parse(dateTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.WEEK_OF_YEAR, +addWeekCount);
        return formatDate(type, calendar.getTime());
    }

    /**
     * 对时间添加减少天数
     *
     * @param dateTime    需要修改的时间
     * @param addDayCount 需要增加减少的天数
     * @param type        需要得到的格式 TimeUtil的静态变量 详情见的注释
     * @return 返回修改后的时间
     */
    public static String getDateAddDay(String dateTime, int addDayCount, int type) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdfCommon.parse(dateTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DATE, +addDayCount);
        return formatDate(type, calendar.getTime());
    }

    /**
     * 获得本月第一天?
     */
    public static String getMonthOfFirst() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return formatDate(3, c.getTime());
    }

    /**
     * 获得本月最后一天
     */
    public static String getMonthOfLast() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return formatDate(3, ca.getTime());
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt 当前时间
     */
    public static int getWeekOfDate(Date dt) {
        Integer[] weekDays = {7, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 得到本周周一
     *
     * @return yyyy-MM-dd
     */
    public static String getMondayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        c.add(Calendar.DATE, -dayOfWeek + 1);
        return formatDate(3, c.getTime());
    }

    /**
     * 得到本周周日
     *
     * @return yyyy-MM-dd
     */
    public static String getSundayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        c.add(Calendar.DATE, -dayOfWeek + 7);
        return formatDate(3, c.getTime());
    }

	/*public static void main(String[] args){
        Date date = new Date();
		String dateTime = sdfCommon.format(date);
		System.out.println(getDateAddDay(dateTime,-333,5));
		System.out.println(getDateAddWeek(dateTime,-60,5));
		System.out.println(getDateAddYear(dateTime,-31,5));
		System.out.println(getDateAddHour(dateTime,-333,5));
		System.out.println(getDateAddMinute(dateTime,-333,5));
		System.out.println(getDateAddMouth(dateTime,-333,5));
		System.out.println(getLastMonthDateTime());
		System.out.println(getThisWeek());
		System.out.println(getBeforeAfterDay(-11,5));
		System.out.println(getLastWeekTime());
		System.out.println(getYesterdayDateTime());
		System.out.println(getTodayDateTime());
		System.out.println(getDateTime(1));
		System.out.println(getDateTime(2));
		System.out.println(getDateTime(3));
		System.out.println(getDateTime(4));
		System.out.println(getDateTime(5));
	}*/

}
