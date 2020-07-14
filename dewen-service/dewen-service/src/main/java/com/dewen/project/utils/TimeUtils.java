package com.dewen.project.utils;

import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.*;

/**
 * 时间日期操作工具类
 *
 * @author harlin
 */
public class TimeUtils {

    public static final String YYYYMMDDRES = "yyyy/MM/dd";

    /**
     * 获取距离当前时间的天数
     *
     * @param date
     * @return
     */
    public static long getDiffByDay(final Date date) {
        long cha = System.currentTimeMillis() - date.getTime();
        long dayChat = cha / 86400000;
        long dayCha = dayChat % 365;
        return dayCha;
    }

    /**
     * 获取距离当前时间的年数
     *
     * @param date
     * @return
     */
    public static long getDiffByYear(final Date date) {
        long cha = System.currentTimeMillis() - date.getTime();
        long dayChat = cha / 86400000;
        long yearCha = dayChat / 365;
        return yearCha;
    }

    /**
     * 指定時間加秒
     *
     * @param date
     * @param second
     * @return
     */
    public static Date addSecond(Date date, final int second) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, second);
        return c.getTime();
    }

    /**
     * 指定时间加分钟
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date addMinute(Date date, final int minute) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, minute);
        return c.getTime();
    }

    /**
     * 获取指定日期月份的最后一天时间
     *
     * @param date
     * @return
     */
    public static int getMonthLastDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 0);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static String[] getMonthCalendar() {
        return getMonthCalendar(getNowDate());
    }

    public static String[] getMonthCalendar(final String month) throws Exception {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
        Date date = sf.parse(month);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 15);
        return getMonthCalendar(c.getTime());
    }

    public static String[] getMonthCalendar(Date date) {
        String[] dates = new String[2];
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int curry = c.get(Calendar.MONTH);
        // 定位到当月的第一周
        c.set(Calendar.WEEK_OF_MONTH, 1);
        // 循环定位时间到当周的星期天
        while (c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            c.add(Calendar.DATE, -1);
        }
        dates[0] = getDate(c.getTime());
        int after = c.get(Calendar.MONTH);
        if (after < curry) {
            // 夸月了
            c.add(Calendar.MONTH, 2);
        } else {
            c.add(Calendar.MONTH, 1);
        }
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        // 循环定位到星期六
        while (c.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
            c.add(Calendar.DATE, 1);
        }
        dates[1] = getDate(c.getTime());
        return dates;
    }

    public static boolean thanBetween(final String toDay, final Date startDate, final Date endDate) {
        Date toDateStart = TimeUtils.appendStart(toDay);
        Date toDateEnd = TimeUtils.appendEnd(toDay);
        //System.out.println("原始值开始时间：" + getTime(toDateStart));
        //System.out.println("原始值结束时间：" + getTime(toDateEnd));
        //System.out.println("目标值开始时间：" + getTime(startDate));
        //System.out.println("目标值结束时间：" + getTime(endDate));
        if (toDateStart.getTime() <= endDate.getTime() && toDateEnd.getTime() >= startDate.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean thanLess(final String toDay, final String formate) throws Exception {
        SimpleDateFormat sf = new SimpleDateFormat(formate);
        Date date = sf.parse(toDay);
        if (date.getTime() < System.currentTimeMillis()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean thanLess(final String toDay) throws Exception {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sf.parse(toDay);
        if (date.getTime() < System.currentTimeMillis()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串日期是否是当天
     *
     * @param day
     * @return
     */
    public static Boolean isToday(final String day) {
        if (day.equalsIgnoreCase(getDate())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取当天是本周星期几（中文返回）
     *
     * @return
     */
    public static String getWeekOfDate() {
        return getWeekOfDate(new Date());
    }

    /**
     * 获取指定日期是星期几（中文返回）
     *
     * @param dt
     * @return
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取两个时间之间小时差
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getDiffHour(final Date startDate, final Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        return diff / 3600000;
    }

    /**
     * 获取两个时间之间年份差
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getDiffYear(final Date startDate, final Date endDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        int syear = c.get(Calendar.YEAR);
        c.setTime(endDate);
        int eyear = c.get(Calendar.YEAR);
        return eyear - syear;
    }

    public static long getDiffYear(final Date endDate) {
        return getDiffYear(new Date(), endDate);
    }

    /**
     * 获取两个时间之间秒数差
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getDiffSeconds(final Date startDate, final Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        return diff / 1000;
    }

    public static long getDiffSeconds(final Date endDate) {
        return getDiffSeconds(new Date(), endDate);
    }

    /**
     * 获取两个时间中每一天的集合
     *
     * @param stime
     * @param etime
     * @return
     */
    public static List<String> getDay(final String stime, final String etime) {
        List<String> datas = new ArrayList<String>();
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateStart = sf.parse(stime);
            Date dateEnd = sf.parse(etime);
            Calendar c = Calendar.getInstance();
            c.setTime(dateStart);
            while (c.getTime().before(dateEnd)) {
                datas.add(sf.format(c.getTime()));
                c.add(Calendar.DAY_OF_MONTH, 1);
            }
            datas.add(etime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datas;
    }

    public static String addMinute(final String startDate, final int minute) {
        Calendar c = Calendar.getInstance();
        c.setTime(getDate(startDate));
        c.add(Calendar.MINUTE, minute);
        return getTime(c.getTime());
    }

    /**
     * 获取两个时间中每一天的集合
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<String> getDays(final Date startDate, final Date endDate) {
        List<String> datas = new ArrayList<String>();
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(startDate);
            while (c.getTime().before(endDate)) {
                datas.add(sf.format(c.getTime()));
                c.add(Calendar.DAY_OF_MONTH, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datas;
    }

    /**
     * 获取一月前的今天
     *
     * @return
     */
    public static String getBefore1MonthDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        return getTime(c.getTime());
    }

    /**
     * 获取三月前的今天
     *
     * @return
     */
    public static String getBefore3MonthDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -3);
        return getTime(c.getTime());
    }

    /**
     * 获取两个时间之间毫秒差
     *
     * @param date
     * @return
     */
    public static long getUsedTime(Date date) {
        Date newdate = new Date();
        long cha = newdate.getTime() - date.getTime();
        return cha;
    }

    /**
     * 获取指定时间在月中的某一天
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定日期的月份
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取指定日期的年份
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 获取当前日期的yyyy-MM
     *
     * @return
     */
    public static String getMonth() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
        return sf.format(new Date());
    }

    /**
     * 获取指定时间的yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String getDate(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(date);
    }

    public static Date getCurrentDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static String getDate(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(date);
    }

    /**
     * 获取当前时间的yyyy-MM-dd
     *
     * @return
     */
    public static String getDate() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(new Date());
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date getNowDate() {
        return new Date();
    }

    public static Date getNowDateStart() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    public static Date getNowDateEnd() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    /**
     * 获取指定时间指定格式的字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String getTime(Date date, String format) {
        try {
            if (date != null) {
                SimpleDateFormat sf = new SimpleDateFormat(format);
                return sf.format(date);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取指定字符串，指定格式的日期对象
     *
     * @param date
     * @param format
     * @return
     */
    public static Date getDate(String date, String format) throws Exception {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.parse(date);
    }

    /**
     * 获取当前日期的毫秒数
     *
     * @return
     */
    public static long getLongTime() {
        Date date = new Date();
        return date.getTime();
    }

    /**
     * 获取指定字符串时间的日期对象（yyyy-MM-dd HH:mm:ss）
     *
     * @param time
     * @return
     */
    public static Date getDate(String time) {
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sf.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取指定日期的字符串(yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @return
     */
    public static String getTime(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(date);
    }

    /**
     * 获取当前时间的字符串格式(yyyy-MM-dd HH:mm:ss)
     *
     * @return
     */
    public static String getTime() {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(date);
    }

    public static String dateAppendEnd(final String date) {
        return dateAppend(date, "23:59:59");
    }

    public static String dateAppendStart(final String date) {
        return dateAppend(date, "00:00:00");
    }

    public static Date appendEnd(final String date) {
        return TimeUtils.getDate(dateAppend(date, "23:59:59"));
    }

    public static Date appendStart(final String date) {
        return TimeUtils.getDate(dateAppend(date, "00:00:00"));
    }

    public static String dateAppend(final Date sourceDate, final String toDay, final String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        String time = sf.format(sourceDate);
        StringBuffer buffer = new StringBuffer(toDay);
        return buffer.append(" ").append(time).toString();
    }

    public static String dateAppend(final String date, final String time) {
        StringBuffer buffer = new StringBuffer(date);
        return buffer.append(" ").append(time).toString();
    }

    /**
     * 將時間截止到分
     *
     * @param date
     * @param time
     * @return
     */
    public static String dateAppendSubMin(final String date, final String time) {
        StringBuffer buffer = new StringBuffer(date);
        String t = time.substring(0, time.lastIndexOf(":"));
        return buffer.append(" ").append(t).toString();
    }

    /**
     * 将时间截取到分
     *
     * @param date
     * @return
     */
    public static String dateAppendSubMin(final String date) {
        return date.substring(0, date.lastIndexOf(":"));
    }

    public static String replaceYear(final String time, final int year) {
        Calendar c = Calendar.getInstance();
        c.setTime(getDate(time));
        c.set(Calendar.YEAR, year);
        return getTime(c.getTime());
    }

    public static int getYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    public static Boolean compareDateLE(final String date1, final String date2) {
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = sf.parse(date1);
            Date d2 = sf.parse(date2);
            if (d1.getTime() < d2.getTime()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean compareDateGE(final String date1, final String date2) {
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = sf.parse(date1);
            Date d2 = sf.parse(date2);
            if (d1.getTime() > d2.getTime()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int getCalendarWeekDay(int dayOfWeek) {
        int c = 0;
        switch (dayOfWeek) {
            case 1:
                c = Calendar.MONDAY;
                break;
            case 2:
                c = Calendar.TUESDAY;
                break;
            case 3:
                c = Calendar.WEDNESDAY;
                break;
            case 4:
                c = Calendar.THURSDAY;
                break;
            case 5:
                c = Calendar.FRIDAY;
                break;
            case 6:
                c = Calendar.SATURDAY;
                break;
            case 7:
                c = Calendar.SUNDAY;
                break;
        }
        return c;
    }

    /**
     * 将String的yyyy-MM-dd转成String的dd/MM/yyyy形式。
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static String strDateFormat(String strDate) {
        if (StringUtils.isEmpty(strDate)) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dateStr = new SimpleDateFormat("dd/MM/yyyy").format(date);
        return dateStr;
    }

    public static int getTimeMillis() {
        return Integer.valueOf(String.valueOf(getNowDate().getTime()).substring(0, 10));
    }

    public static int getTimeMillis(final Date date) {
        return Integer.valueOf(String.valueOf(date.getTime()).substring(0, 10));
    }

    public static int getTimeMillis(final String time, final String format) throws Exception {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        Date date = sf.parse(time);
        return Integer.valueOf(String.valueOf(date.getTime()).substring(0, 10));
    }

    public static Date strFormat(String strDate, String format) {
        if (strDate == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date strtodate = null;
        try {
            strtodate = formatter.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dateStr = new SimpleDateFormat(format).format(strtodate);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate2 = formatter.parse(dateStr, pos);
        return strtodate2;
    }

    public static Date getNowDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        Date currentTime_2 = null;
        try {
            currentTime_2 = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return currentTime_2;
    }

    public static Date getStartTimeOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getEndTimeOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * x天
     * x时
     * x分
     * x天x分
     * ...
     *
     * @param diff
     * @return
     */
    public static String getSimpleTimeConsumption(long diff) {
        long day = diff / (24 * 60 * 60 * 1000);
        long hour = (diff / (60 * 60 * 1000) - day * 24);
        long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        StringBuilder stringBuffer = new StringBuilder();
        if (day > 0) {
            stringBuffer.append(day).append("天");
        }
        if (hour > 0) {
            stringBuffer.append(hour).append("时");
        }
        if (min > 0) {
            stringBuffer.append(min).append("分");
        }
        return stringBuffer.toString();
    }

    /**
     * x天x时x分x秒
     *
     * @param diff
     * @return
     */
    public static String getTimeConsumption(long diff) {
        long day = diff / (24 * 60 * 60 * 1000);
        long hour = (diff / (60 * 60 * 1000) - day * 24);
        long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        StringBuilder stringBuffer = new StringBuilder();
        if (day > 0) {
            stringBuffer.append(day).append("天");
        }
        if (hour > 0 || stringBuffer.length() > 0) {
            stringBuffer.append(hour).append("时");
        }
        if (min > 0 || stringBuffer.length() > 0) {
            stringBuffer.append(min).append("分");
        }
        if (s > 0 || stringBuffer.length() > 0) {
            stringBuffer.append(s).append("秒");
        }
        return stringBuffer.toString();
    }

    /**
     * x天x时x分x秒
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static String getTimeConsumption(Date startDate, Date endDate) {
        if (null == startDate) {
            return null;
        }
        if (null == endDate) {
            endDate = new Date();
        }
        long l = endDate.getTime() - startDate.getTime();
        return getTimeConsumption(l);
    }

    public static String getCurrentStringDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    public static String formatDateToTimestamp(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String formatDateToDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static List<String> getBetweenForDay(Date startTime, Date endTime) {
        List<String> list = Lists.newArrayList();
        long between = ChronoUnit.DAYS.between(startTime.toInstant(), endTime.toInstant());
        Calendar instance;
        for (int i = 0; i <= between; i++) {
            instance = Calendar.getInstance();
            instance.setTime(startTime);
            instance.add(Calendar.DAY_OF_MONTH, i);
            list.add(getDate(instance.getTime()));
        }
        return list;
    }

    public static List<String> getBetweenForWeek(Date startTime, Date endTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-w");
        List<String> list = Lists.newArrayList();
        Calendar instance = Calendar.getInstance();
        instance.setTime(startTime);
        instance.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        list.add(simpleDateFormat.format(instance.getTime()));
        while (true) {
            instance.add(Calendar.WEEK_OF_YEAR, 1);
            if (instance.getTime().after(endTime)) break;
            list.add(simpleDateFormat.format(instance.getTime()));
        }
        return list;
    }

    public static String getBeginDateForWeek(int year, int week, boolean isEnd) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(YYYYMMDDRES);
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        LocalDateTime with = LocalDateTime.now().withYear(year).with(weekFields.weekOfYear(), week).with(weekFields.dayOfWeek(), isEnd ? 7 : 1);
        return dateTimeFormatter.format(with);
    }

    public static List<String> getBetweenForMonth(Date startTime, Date endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        List<String> list = Lists.newArrayList();
        Calendar instance = Calendar.getInstance();
        instance.setTime(startTime);
        while (instance.getTime().before(endTime) || sdf.format(endTime).equals(sdf.format(instance.getTime()))) {
            list.add(sdf.format(instance.getTime()));
            instance.add(Calendar.MONTH, 1);
        }
        return list;
    }

    public static Date parseTo(String date, String pattern) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String formatTo(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public static String getBeforeYearMonth(String yearMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseTo(yearMonth, "yyyy-MM"));
        calendar.add(Calendar.YEAR, -1);
        return formatTo(calendar.getTime(), "yyyy-MM");
    }

    public static Date getDateByDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

}