package com.dog.utils.common;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期处理类
 */
public class DateUtils {

    private final static String dateFormat = "yyyy-MM-dd";
    public static final String DATE_FORMAT_A_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss"; //默认日期时间模式

    /**
     * 按照指定格式返回格式好的当前日期
     * @param dateFormat 默认yyyy-MM-dd
     * @return
     */
    public static String getCurrentDateString(String dateFormat) {

        return DateUtils.format(new Date(), dateFormat);
    }

    /**
     * 说明 将日期格式化字符串，为null的返回空字符串
     * @param date
     * @return
     */
    public static String format(Date date) {
        if (null == date) return "";
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
        return sf.format(date);
    }

    /**
     * 说明 将日期格式化字符串，为null的返回空字符串
     * @param date       日期
     * @param dateFormat 格式化字符串，比如：yyyy-MM-dd
     * @return
     */
    public static String format(Date date, String dateFormat) {
        if (null == dateFormat || "".equals(dateFormat)) return DateUtils.format(date);
        if (null == date) return "";
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
        return sf.format(date);
    }

    /**
     * @param source 要进行解析的源字符串
     * @return
     * @说明 将指定的字符串格解析成日期类型，格式默认为：yyyy-MM-dd
     */
    public static Date parase(String source) {
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
        try {
            return sf.parse(source);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
        return null;
    }

    /**
     * @param source     要进行解析的源字符串
     * @param dateFormat 要解析的日期格式。
     * @return
     * @说明 将指定的字符串格解析成日期类型 例：如果日期source=20131210,则dateFormat应为：yyyyMMdd,两个应对应
     */
    public static Date parase(String source, String dateFormat) {
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
        try {
            return sf.parse(source);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
        return null;
    }

    /**
     * @param date
     * @param days
     * @说明 对指定的日期增加或减少指定的天数
     */
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    /**
     * @param date
     * @param days
     * @说明 对指定的日期增加或减少指定的天数
     */
    public static Calendar addDays(Calendar date, int days) {
        date.add(Calendar.DAY_OF_MONTH, days);
        return date;
    }

    /**
     * @param date
     * @param months
     * @return
     * @说明 对指定的日期增加或减少指定的月数
     */
    public static Date addMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.MONTH, months);

        return cal.getTime();
    }

    /**
     * @param date
     * @param months
     * @return
     * @说明 对指定的日期增加或减少指定的月数
     */
    public static Calendar addMonths(Calendar date, int months) {
        date.add(Calendar.MONTH, months);

        return date;
    }

    /**
     * @param date
     * @param hours
     * @return
     * @说明 对指定的日期增加或减少指定的小时数
     */
    public static Date addHours(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.HOUR_OF_DAY, hours);

        return cal.getTime();
    }

    /**
     * @param date
     * @param hours
     * @return
     * @说明 对指定的日期增加或减少指定的小时数
     */
    public static Calendar addHours(Calendar date, int hours) {
        date.add(Calendar.HOUR_OF_DAY, hours);
        return date;
    }

    /**
     * @return
     * @说明 以字符串形式返回当前时间的毫秒数
     */
    public static String getTimeMillions() {
        Calendar cal = Calendar.getInstance();
        long lt = cal.getTimeInMillis();

        return String.valueOf(lt);
    }

    /**
     * 获取当前月的第一天
     * @return 当前月的第一天
     */
    public static String getMonthFirstDay() {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();

        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first);
        return str.toString();

    }

    /**
     * 获取当前月的最后一天
     * @return 当前月的最后一天
     */
    public static String getMonthLastDay() {

        Calendar calendar = Calendar.getInstance();
        // 最后一天
        int maxday = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(Calendar.DATE, maxday);

        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Date theDate = calendar.getTime();
        String s = df.format(theDate);
        StringBuffer str = new StringBuffer().append(s);
        return str.toString();

    }

    /**
     * 获取当前月的第一天，精确到时分秒
     * @return 当前月的第一天，精确到时分秒
     */
    public static Date getFirstDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();
        return date;

    }

    /**
     * 获得往数据库字段类型为Date型时，插入的时间
     * @param date       默认为当前日期，如果为空时 方法会自动new Date()
     * @param dateFormat 默认为yyyy-MM-dd
     * @return
     */
    public static java.sql.Date paraseSqlDate(String date, String dateFormat) {
        try {
            if (date == null || date.length() == 0) {
                return new java.sql.Date(new Date().getTime());
            } else {
                if (dateFormat == null) dateFormat = DateUtils.dateFormat;
                SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
                Date d = sf.parse(date);
                return new java.sql.Date(d.getTime());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    /**
     * 获取当前的年份
     * @return
     */
    public static String getCurrentYear() {
        Calendar now = Calendar.getInstance();
        return String.valueOf(now.get(Calendar.YEAR));
    }

    /**
     * 获取当前的年月
     * @return
     */
    public static String getCurrentYearMonth() {
        return getCurrentYear() + getCurrentMonth();
    }

    /**
     * 获取当前的月份
     * @return
     */
    public static String getCurrentMonth() {
        Calendar now = Calendar.getInstance();
        return String.valueOf(now.get(Calendar.MONTH) + 1);
    }


    /**
     * 获取当前时间
     *
     * @return Timestamp对象
     */
    public static Timestamp getCurrontTime() {
        Timestamp sqlTimestamp = new Timestamp(new Date().getTime());
        return sqlTimestamp;
    }

    /**
     * 将Date类型转换成String类型
     *
     * @param date Date对象
     * @return 形如:"yyyy-MM-dd HH:mm:ss"
     */
    public static String date2String(Date date) {
        return date2String(date, DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 将Date按格式转化成String
     *
     * @param date    Date对象
     * @param pattern 日期类型
     * @return String
     */
    public static String date2String(Date date, String pattern) {
        if (date == null || pattern == null) {
            return null;
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 将String类型转换成Date类型
     *
     * @param date Date对象
     * @return
     */
    public static Date string2Date(String date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取某日期N天后的日期
     *
     * @param datestr
     * @param day
     * @return
     */
    public static Date getBeforeAfterDate(String datestr, int day) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
        java.sql.Date olddate = null;
        try {
            df.setLenient(false);
            olddate = new java.sql.Date(df.parse(datestr).getTime());
        } catch (ParseException e) {
            throw new RuntimeException("日期转换错误");
        }
        Calendar cal = new GregorianCalendar();
        cal.setTime(olddate);

        int Year = cal.get(Calendar.YEAR);
        int Month = cal.get(Calendar.MONTH);
        int Day = cal.get(Calendar.DAY_OF_MONTH);

        int NewDay = Day + day;

        cal.set(Calendar.YEAR, Year);
        cal.set(Calendar.MONTH, Month);
        cal.set(Calendar.DAY_OF_MONTH, NewDay);

        return new Date(cal.getTimeInMillis());
    }

    /**
     * 计算两个日期差的天数
     *
     * @param fDate
     * @param oDate
     * @return
     */
    public static int daysBetween(Date fDate, Date oDate) {
        Calendar cNow = Calendar.getInstance();
        Calendar cReturnDate = Calendar.getInstance();
        cNow.setTime(oDate);
        cReturnDate.setTime(fDate);
        cNow.set(Calendar.HOUR_OF_DAY, 0);
        cNow.set(Calendar.MINUTE, 0);
        cNow.set(Calendar.SECOND, 0);
        cReturnDate.set(Calendar.HOUR_OF_DAY, 0);
        cReturnDate.set(Calendar.MINUTE, 0);
        cReturnDate.set(Calendar.SECOND, 0);
        long todayMs = cNow.getTimeInMillis();
        long returnMs = cReturnDate.getTimeInMillis();
        long intervalMs = todayMs - returnMs;
        return (int) (intervalMs / (1000 * 86400));
    }

    /**
     * @return
     * @Description: 获取当前日期的前一天
     * @ReturnType String
     * @author: liyl
     * @Created 2015年11月13日 下午5:11:14
     */
    public static Date currentBeforeDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     * @return
     * @Description: 获取当前日期的后一天
     * @ReturnType Date
     * @author: liyl
     * @Created 2015年11月13日 下午5:14:54
     */
    public static Date currentNextDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取时间的星期
     *
     * @param dt
     * @return
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"SUND_FLAG", "MOND_FLAG", "TUES_FLAG", "WEDN_FLAG", "THUR_FLAG", "FRID_FLAG", "SATU_FLAG"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 时间比大小
     *
     * @param DATE1
     * @param DATE2
     * @param pattern
     * @return
     */
    public static int compareDate(String DATE1, String DATE2, String pattern) {

        DateFormat df = new SimpleDateFormat(pattern);
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
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
     * 在一个时间上加上或减去分钟
     *
     * @param date long
     * @param i    int
     * @return Date
     */
    public static Date addOrMinusMinutes(Date date, int i) {
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(GregorianCalendar.MINUTE, i);
        rtn = cal.getTime();
        return rtn;
    }

    /**
     * 转换时间格式
     * 获取开始时间TimeStamp格式
     * @param date
     * @return
     */
    public static Timestamp getStartTimeStamp(String date) throws ParseException{
        if (StringUtils.isEmpty(date)) {
            return null;
        }else if(date.length()<=10)
        {
            date+=" 00:00:00";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.parse(date);
        return Timestamp.valueOf(date);
    }

    /**
     * 获取结束时间TimeStamp时间格式
     * @param date
     * @return
     * @throws Exception
     */
    public static Timestamp getEndTimeStamp(String date) throws ParseException{
        if (StringUtils.isEmpty(date)) {
            return null;
        }else if(date.length()<=10)
        {
            date+=" 23:59:59";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.parse(date);
        return Timestamp.valueOf(date);
    }

    /**
     * 日期格式
     **/
    public interface DATE_PATTERN {
        String HHMMSS = "HHmmss";
        String HH_MM_SS = "HH:mm:ss";
        String HH_MM = "HH:mm";
        String YYYY = "yyyy";
        String YYYYMMDD = "yyyyMMdd";
        String YYYY_MM_DD = "yyyy-MM-dd";
        String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
        String YYMMDDHHMMSS = "yyMMddHHmmss";
        String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
        String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
        String MM = "MM";
    }

    public static void main(String[] args) throws Exception{
        DateUtils.getStartTimeStamp("2017-10-10");
        System.out.println(DateUtils.date2String(getBeforeAfterDate(DateUtils.date2String(new Date()), 90)));

        String ss = getCurrentDateString("MM");
        System.out.println(ss);
    }
}
