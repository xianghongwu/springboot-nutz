package com.xhw.springbootnutz.util;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.time.FastDateFormat;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 *
 * @Description: 日期工具
 * @Author:         xhw
 * @CreateDate:     2019-10-11 9:53
 */
public class DateUtils {


    public static final FastDateFormat FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");
    public static final FastDateFormat FORMAT_DATETIME = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    public static final FastDateFormat FORMAT_TIME = FastDateFormat.getInstance("HH:mm:ss");

    public static final String[] WEEK = {"星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    /**
     * 获取当前系统时间
     *
     * @return
     */
    public static Timestamp getCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取当前年
     *
     * @return
     */
    public static Integer getCurrentYear() {
        Calendar CALENDAR = Calendar.getInstance();
        return CALENDAR.get(Calendar.YEAR);
    }

    /**
     * 获取当前月
     *
     * @return
     */
    public static Integer getCurrentMonth() {
        Calendar CALENDAR = Calendar.getInstance();
        return CALENDAR.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前日
     *
     * @return
     */
    public static Integer getCurrentDay() {
        Calendar CALENDAR = Calendar.getInstance();
        return CALENDAR.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前是星期几
     *
     * @return
     */
    public static Integer getCurrentWeekResultInt() {
        Calendar CALENDAR = Calendar.getInstance();
        CALENDAR.setTime(new Date());
        int w = CALENDAR.get(Calendar.DAY_OF_WEEK) - 1;
        if (w <= 0) {
            w = 7;
        }
        return w;
    }

    /**
     * 获取当前是星期几
     *
     * @return
     */
    public static String getCurrentWeek() {
        Calendar CALENDAR = Calendar.getInstance();
        CALENDAR.setTime(new Date());
        int w = CALENDAR.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return WEEK[w];
    }

    /**
     * 根据日期获取对应的星期数
     *
     * @param time
     * @return
     */
    public static Integer getWeekNumberByDate(String time) {
        Calendar CALENDAR = Calendar.getInstance();
        Integer week = 0;
        try {
            CALENDAR.setTime(FORMAT.parse(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int w = CALENDAR.get(Calendar.DAY_OF_WEEK) - 1;
        if (w <= 0) {
            week = 7;
        } else {
            week = w;
        }
        return week;
    }

    /**
     * 根据日期获取对应的星期数
     *
     * @return
     */
    public static Integer getCurrentWeekNumber() {
        Calendar CALENDAR = Calendar.getInstance();
        Integer week = 0;
        try {
            CALENDAR.setTime(getCurrentTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        int w = CALENDAR.get(Calendar.DAY_OF_WEEK) - 1;
        if (w <= 0) {
            week = 7;
        } else {
            week = w;
        }
        return week;
    }

    /**
     * 根据日期获取星期数
     *
     * @param time
     * @return
     */
    public static String getWeekByDate(String time) {
        Calendar CALENDAR = Calendar.getInstance();
        try {
            CALENDAR.setTime(FORMAT.parse(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int w = CALENDAR.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return WEEK[w];
    }



    /**
     * 获取两个日期相差天数
     *
     * @param startTime
     * @param finishTime
     * @return
     */
    public static Integer getDaysDiff(Date startTime, Date finishTime) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(startTime);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(finishTime);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {//同一年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {//闰年
                    timeDistance += 366;
                } else {//不是闰年
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else {
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }

    /**
     * 获取两个日期相差月份数
     *
     * @param finishTime
     * @param startTime
     * @return
     */
    public static Integer getMonthDiff(Date finishTime, Date startTime) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(finishTime);
        c2.setTime(startTime);
        if (c1.getTimeInMillis() < c2.getTimeInMillis()) return 0;
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值 假设 d1 = 2015-8-16  d2 = 2011-9-30
        int yearInterval = year1 - year2;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if (month1 < month2 || month1 == month2 && day1 < day2) yearInterval--;
        // 获取月数差值
        int monthInterval = (month1 + 12) - month2;
        if (day1 < day2) monthInterval--;
        monthInterval %= 12;
        if (yearInterval * 12 + monthInterval == 0) {//不足一个月按一个月计算
            return 1;
        } else {
            return yearInterval * 12 + monthInterval;
        }
    }

    /**
     * 根据字符串日期转换为日期
     *
     * @param time
     * @return
     */
    public static Date getDateTimeByString(String time) {
        Date date = null;
        try {
            date = FORMAT_DATETIME.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
    /**
     * 根据字符串日期转换为日期
     *
     * @param time
     * @return
     */
    public static Date getDateByString(String time) {
        Date date = null;
        try {
            date = FORMAT.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 根据日期转换为字符串日期 年月日
     *
     * @param time
     * @return
     */
    public static String getStringByDate(Date time) {
        String date=null;
        try {
            date=FORMAT.format(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取昨天日期
     * @return
     */
    public static String getlastDate() {
        Calendar   cal   =   Calendar.getInstance();
        cal.add(Calendar.DATE,   -1);
        String yesterday = FORMAT.format(cal.getTime());
        System.out.println(yesterday);
        return yesterday;
    }



    /**
     * 根据日期转换为字符串日期 时分秒
     *
     * @param time
     * @return
     */
    public static String getStringtimeByDate(Date time) {
        String date=null;
        try {
            date=FORMAT_TIME.format(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 根据字符串日期转换为日期
     *
     * @param time
     * @return
     */
    public static Date getDateByStringtime(String time) {
        Date date = null;
        try {
            date = FORMAT_TIME.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 根据字符串截取年份
     *
     * @param time
     * @return
     */
    public static Integer getYearByTime(String time) {
        if (null == time) {
            return null;
        }
        String year = time.substring(0, 4);
        return Integer.valueOf(year);
    }

    /**
     * 根据字符串截取月份
     *
     * @param time
     * @return
     */
    public static Integer getMonthByTime(String time) {
        if (null == time) {
            return null;
        }
        String month = null;
        String[] timeArray = time.split("-");
        month = timeArray[1];
        return Integer.valueOf(month);
    }
    /**
     * 根据字符串截取天
     *
     * @param time
     * @return
     */
    public static Integer getDayByTime(String time) {
        if (null == time) {
            return null;
        }
        String day = null;
        String[] timeArray = time.split("-");
        day = timeArray[2].split(" ")[0];
        return Integer.valueOf(day);
    }
    /**
     * 根据日期返回所属本月第几周
     *
     * @param time
     * @return
     */
    public static Integer getWeek(Date time) {
        Calendar CALENDAR = Calendar.getInstance();
        try {
            CALENDAR.setTime(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CALENDAR.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 根据日期返回所属本月第几周
     *
     * @param time
     * @return
     */
    public static Integer getWeek(String time) {
        Calendar CALENDAR = Calendar.getInstance();
        try {
            CALENDAR.setTime(FORMAT.parse(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CALENDAR.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 返回当前日期所属本月第几周
     *
     * @return
     */
    public static Integer getWeek() {
        Calendar CALENDAR = Calendar.getInstance();
        try {
            CALENDAR.setTime(getCurrentTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CALENDAR.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 获取指定月份的最后一天
     * @return
     */
    public static Integer getLastDayOfMonth(String date) {
        if (null == date) {
            return null;
        }
        String day = null;
        String[] timeArray = date.split("-");
        Calendar a=Calendar.getInstance();
        a.set(Calendar.YEAR, Integer.parseInt(timeArray[0]));
        a.set(Calendar.MONTH, Integer.parseInt(timeArray[1])-1);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int MaxDate=a.get(Calendar.DATE);
        return MaxDate;

    }
    /**
     * 当前时间所在一周的周一和周日时间
     * @return
     */
    public static Map<String,String> getWeekDate() {

        Map<String,String> map = new HashedMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if(dayWeek==1){
            dayWeek = 8;
        }
       // System.out.println("要计算日期为:" + sdf.format(cal.getTime())+ DateUtils.getWeek()); // 输出要计算日期
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        Date mondayDate = cal.getTime();
        String weekBegin = sdf.format(mondayDate);
//        System.out.println("所在周星期一的日期：" + weekBegin);
        cal.add(Calendar.DATE, 4 +cal.getFirstDayOfWeek());
        Date sundayDate = cal.getTime();
        String weekEnd = sdf.format(sundayDate);
//        System.out.println("所在周星期日的日期：" + weekEnd);
        map.put("mondayDate", weekBegin);
        map.put("sundayDate", weekEnd);
        return map;
    }
    /**
     * 当前时间所在一周的周一和周五时间
     * @return
     */
    public static Map<String,String> getWeekDate_5() {

        Map<String,String> map = new HashedMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if(dayWeek==1){
            dayWeek = 8;
        }
        // System.out.println("要计算日期为:" + sdf.format(cal.getTime())+ DateUtils.getWeek()); // 输出要计算日期
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        Date mondayDate = cal.getTime();
        String weekBegin = sdf.format(mondayDate);
//        System.out.println("所在周星期一的日期：" + weekBegin);
        cal.add(Calendar.DATE, 2 +cal.getFirstDayOfWeek());
        Date sundayDate = cal.getTime();
        String weekEnd = sdf.format(sundayDate);
//        System.out.println("所在周星期日的日期：" + weekEnd);
        map.put("mondayDate", weekBegin);
        map.put("sundayDate", weekEnd);
        return map;
    }

    /**
     * 获取指定时间的一周的日期
     * @return
     */
    public static Map<String,String> getWeekDateBydate(String dateStr) {

        Map<String,String> map = new HashedMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date reDate = null;
        try {
            reDate = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(reDate);
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if(dayWeek==1){
            dayWeek = 8;
        }
        // System.out.println("要计算日期为:" + sdf.format(cal.getTime())+ DateUtils.getWeek()); // 输出要计算日期
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        Date mondayDate = cal.getTime();
        String weekBegin = sdf.format(mondayDate);
//        System.out.println("所在周星期一的日期：" + weekBegin);
        cal.add(Calendar.DATE, 4 +cal.getFirstDayOfWeek());
        Date sundayDate = cal.getTime();
        String weekEnd = sdf.format(sundayDate);
//        System.out.println("所在周星期日的日期：" + weekEnd);
        map.put("mondayDate", weekBegin);
        map.put("sundayDate", weekEnd);
        return map;
    }
    /**
     * 获取指定时间的一周的日期 5天
     * @return
     */
    public static Map<String,String> getWeekDateBydate_5(String dateStr) {

        Map<String,String> map = new HashedMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date reDate = null;
        try {
            reDate = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(reDate);
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if(dayWeek==1){
            dayWeek = 8;
        }
        // System.out.println("要计算日期为:" + sdf.format(cal.getTime())+ DateUtils.getWeek()); // 输出要计算日期
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        Date mondayDate = cal.getTime();
        String weekBegin = sdf.format(mondayDate);
//        System.out.println("所在周星期一的日期：" + weekBegin);
        cal.add(Calendar.DATE, 2 +cal.getFirstDayOfWeek());
        Date sundayDate = cal.getTime();
        String weekEnd = sdf.format(sundayDate);
//        System.out.println("所在周星期日的日期：" + weekEnd);
        map.put("mondayDate", weekBegin);
        map.put("sundayDate", weekEnd);
        return map;
    }
    /**
     * 计算两个时间区间是否有交集(须确保开始时间小于结束时间)
     * @param startTime 1区间开始时间
     * @param endTime 1区间结束时间
     * @param start 2区间开始时间
     * @param end 2区间结束时间
     * @return
     */
    public static boolean temporalInterval(Date startTime,Date endTime,Date start,Date end){
        if((startTime.getTime()<=start.getTime()) && endTime.getTime()>=start.getTime()){
            return true;
        }else if((startTime.getTime()>=start.getTime())&& startTime.getTime()<=end.getTime()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 获取当前是星期几
     *
     * @return
     */
    public static Integer getCurrentWeekResultInt(String time) {
        Calendar CALENDAR = Calendar.getInstance();
        Integer week = 0;
        try {
            CALENDAR.setTime(FORMAT.parse(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int w = CALENDAR.get(Calendar.DAY_OF_WEEK) - 1;
        if (w <= 0) {
            w = 7;
        }
        return w;
    }

    /**
     * 判断传入的时分秒是否比当前日期的时分秒大
     *
     * @return
     */
    public static boolean gettimeBig(String time) {
        String new_time=getStringByDate(getCurrentTime())+" "+time;
        Date dateByString = getDateTimeByString(new_time);
        if(dateByString.getTime()>getCurrentTime().getTime()){
            return true;
        }
        return  false;

    }

    public static void main(String[] args) {
        /* getDaysDiff(getCurrentTime(),getDateByString("2018-07-10 13:00"));
        System.out.println(getCurrentDay());
        System.out.println(getWeekByDate("2018-6-24"));
        System.out.println(getWeekNumberByDate("2018-6-24"));
        System.out.println(getWeek("2018-6-26"));
        System.out.println(getCurrentWeek());*/
       //getDaysDiff(DateUtils.getCurrentTime(), DateUtils.getDateByString("2018-07-30"));
        //System.out.println(getLastDayOfMonth("2018-07"));
        System.out.println(getWeekDate_5());
    }

}
