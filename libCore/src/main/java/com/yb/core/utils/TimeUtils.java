package com.yb.core.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Avenger on 2019/10/16.
 *
 * @Description:时间相关的工具类
 */
public class TimeUtils {//某天的开始时间---获取当天的开始时间
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static String getCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    //某天的开始时间---获取一周时间
    public static List<String> getWeek() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<String> weeks = new ArrayList<>();
        Date myToday = getBeginDateOfToday();
        //一周的日期
        weeks.add(simpleDateFormat.format(myToday));
        weeks.add(simpleDateFormat.format(myToday.getTime() - 86400000L));
        weeks.add(simpleDateFormat.format(myToday.getTime() - 86400000L * 2));
        weeks.add(simpleDateFormat.format(myToday.getTime() - 86400000L * 3));
        weeks.add(simpleDateFormat.format(myToday.getTime() - 86400000L * 4));
        weeks.add(simpleDateFormat.format(myToday.getTime() - 86400000L * 5));
        weeks.add(simpleDateFormat.format(myToday.getTime() - 86400000L * 6));
        return weeks;
    }

    public static Date getStrToDate(String timeStr){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_PATTERN);
        Date date = null;
        try {
            date = simpleDateFormat.parse(timeStr);
        } catch (ParseException e) {
        }
        return date;
    }

    public static String getBeginDateOfTodayString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(getBeginDateOfToday());
    }

    public static Date getBeginDateOfToday() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    //某天的开始时间---获取昨天的开始时间
    public static Date getBeginDateOfYesterday() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getBeginDateOfToday());
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    //某天的结束时间---获取当天的结束时间
    public static Date getEndDateOfToday() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    //某天的结束时间---获取昨天的结束时间
    public static Date getEndDateOfYesterday() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getEndDateOfToday());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    //某天的开始时间---获取当天的开始时间
    public static String getBeginStringOfToday() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time_string = df.format(getBeginDateOfToday());
        return time_string;
    }

    //某天的结束时间---获取当天的结束时间
    public static String getEndStringOfToday() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time_string = df.format(getEndDateOfToday());
        return time_string;
    }

    public static String getEndStringOfTodayNoTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String time_string = df.format(getEndDateOfToday());
        return time_string;
    }

    //某天的开始时间---获取昨天的开始时间
    public static String getBeginStringOfYesterday(int day) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = new GregorianCalendar();
        cal.setTime(getBeginDateOfToday());
        cal.add(Calendar.DATE, -day);
        String time_string = df.format(cal.getTime());
        return time_string;
    }

    //某天的结束时间---获取昨天的结束时间
    public static String getEndStringOfYesterday(int day) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = new GregorianCalendar();
        cal.setTime(getEndDateOfToday());
        cal.add(Calendar.DAY_OF_MONTH, -day);
        String time_string = df.format(cal.getTime());
        return time_string;
    }

    //获取当前时间的前3天的日期
    public static String getLatelyStringOfThree() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDateOfToday());
        cal.add(Calendar.DATE, -2);
        String time_string = df.format(cal.getTime());
        return time_string;
    }

    //获取当前时间的前7天的日期
    public static String getLatelyStringOfSeven() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDateOfToday());
        cal.add(Calendar.DATE, -6);
        String time_string = df.format(cal.getTime());
        return time_string;
    }

    //获取当前时间的前15天的日期
    public static String getLatelyStringOf15() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDateOfToday());
        cal.add(Calendar.DATE, -14);
        String time_string = df.format(cal.getTime());
        return time_string;
    }

    // 获得本月第一天开始时间
    public static String getStringBeginDayOfCurrentMonth() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();

        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        return day_first;
    }

    //获当前时间的前35天的日期
    public static String getLatelyStringOf35() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDateOfToday());
        cal.add(Calendar.DATE, -34);
        String time_string = df.format(cal.getTime());
        return time_string;
    }

    //获取当前时间的前7天的日期
    public static Date getLatelyDateOfSeven() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getBeginDateOfToday());
        cal.add(Calendar.DAY_OF_MONTH, -6);
        return cal.getTime();
    }

    //获取当前时间的前3天的日期
    public static Date getLatelyDateOfThree() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getBeginDateOfToday());
        cal.add(Calendar.DAY_OF_MONTH, -2);
        return cal.getTime();
    }

    //获取当前时间的前15天的日期
    public static Date getLatelyDateOf15() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getBeginDateOfToday());
        cal.add(Calendar.DAY_OF_MONTH, -14);
        return cal.getTime();
    }

    //获取当前时间的前30天的日期
    public static Date getLatelyDateOf30() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getBeginDateOfToday());
        cal.add(Calendar.DAY_OF_MONTH, -29);
        return cal.getTime();
    }

    //获取当前时间的前N天的日期
    public static String getLatelyStringOfN(int n) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDateOfToday());
        cal.add(Calendar.DATE, -n);
        String time_string = df.format(cal.getTime());
        return time_string;
    }

    public static String getLatelyStringOfNoTime(int n) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDateOfToday());
        cal.add(Calendar.DATE, -n);
        String time_string = df.format(cal.getTime());
        return time_string;
    }

    /**
     * 是不超过了24个小时
     *
     * @param tokenTime 时间戳
     * @return
     */
    public static boolean isExceed24Hours(long tokenTime) {
        long now = System.currentTimeMillis();
        long pass = now - tokenTime;
        double hours = pass * 1.0 / (1000 * 60 * 60);
        return hours >= 24;
    }

    /**
     * 是不超过了23个小时
     *
     * @param tokenTime 时间戳
     * @return
     */
    public static boolean isExceed23Hours(long tokenTime) {
        long now = System.currentTimeMillis();
        long pass = now - tokenTime;
        double hours = pass * 1.0 / (1000 * 60 * 60);
        return hours >= 23;
    }

    /**
     * 秒转换成时分秒
     *
     * @param time
     * @return
     */
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0) {
            return "00:00:00";
        } else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99) {
                    return "99:59:59";
                }
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10) {
            retStr = "0" + i;
        } else {
            retStr = "" + i;
        }
        return retStr;
    }

    /**
     * 将时间转化为时间戳
     *
     * @param input_time
     * @return
     */
    public static long parseData(String input_time) {
        Date date = getDateInString(input_time, null);
        if (date == null) {
            return 0;
        }
        return date.getTime();
    }

    /**
     * 将时间格式化
     * 将字符串的时间转成DATE
     *
     * @return date类型的时间
     */
    public static Date getDateInString(String date, String dateFormat) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        Date strtodate = null;
        try {
            if (dateFormat == null) {
                dateFormat = "yyyy-MM-dd HH:mm:ss";
            }
            SimpleDateFormat format = new SimpleDateFormat(dateFormat);
            strtodate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return strtodate;
    }

    /**
     * 获取前n天日期
     */
    public static String getNearbyDate(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }


    public static int getNowIndex() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = LunarUtil.getYear(calendar);
        return (year) % 12;
    }

    public static String millis2String(long millis) {
        return new SimpleDateFormat(DEFAULT_PATTERN, Locale.getDefault()).format(new Date(millis));
    }

    public static boolean isExceed30Minute(long changeApiTime) {
        long now = System.currentTimeMillis();
        long pass = now - changeApiTime;
        double hours = pass * 1.0 / (1000 * 60 * 60 * 60);
        return hours > 30;
    }

    /**
     * 毫秒格式化为 时间字符串
     *
     * @param time
     * @param dateFormat
     * @return
     */
    public static String getDateDoubleString(long time, String dateFormat) {
        if (time == 0) {
            return "--";
        }
        if (TextUtils.isEmpty(dateFormat)) {
            dateFormat = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.format(time);

    }

    /**
     * 获取系统当前年份
     *
     * @return
     */
    public static int getSysYear() {
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        return year;

    }

    public static String getDateFormat(String oldDate) {
        if (oldDate == null || oldDate == "") {
            return oldDate;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(oldDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return oldDate;
        }
        return format.format(date);
    }
}
