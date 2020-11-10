package cn.codenest.redists.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <h3>ibsthirddata</h3>
 * <p>时间操作相关工具类</p>
 *
 * @author : hyman
 * @date : 2019-11-27 11:37
 **/
public class TimeFunc {
    // type:
    // Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH,
    // Calendar.HOUR, Calendar.MINUTE, Calendar.SECOND
    public static Date AddDate(Date date, int type, int num) {
        Calendar cal = GetCalendar(date);
        cal.add(type, num);
        return cal.getTime();
    }

    // 获取整天时间
    public static Date GetDayDate(Date date) {
        Calendar cal = GetCalendar(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    // 获取整天时间
    public static Date GetDayDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date subHour(Date date, int type, int num) {
        Calendar cal = GetCalendar(date);
        cal.add(type, num);
        return cal.getTime();
    }

    // 获取整年时间
    public static Date GetYearDate(Date date) {
        Calendar cal = GetCalendar(date);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    // 获取整小时时间
    public static Date GetHourDate(Date date) {
        Calendar cal = GetCalendar(date);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    // 获取整半小时时间
    public static Date GetHalfHourDate(Date date) {
        Calendar cal = GetCalendar(date);
        int minute = cal.get(Calendar.MINUTE);
        if (minute >= 30) {
            return AddDate(GetHourDate(date), Calendar.MINUTE, 30);
        } else {
            return GetHourDate(date);
        }
    }

    // 获取整分钟时间
    public static Date GetMinuteDate(Date date) {
        Calendar cal = GetCalendar(date);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date GetYearDate(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, 0, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    // 获取时间在一年中是第几天
    public static int GetDayOfYear(Date date) {
        return GetCalendar(date).get(Calendar.DAY_OF_YEAR);
    }

    // 获取年份
    public static int GetYear(Date date) {
        return GetCalendar(date).get(Calendar.YEAR);
    }

    // 获取月份
    public static int GetMonth(Date date) {
        return GetCalendar(date).get(Calendar.MONTH) + 1;// 需要加1
    }

    // 获取日
    public static int GetDay(Date date) {
        return GetCalendar(date).get(Calendar.DAY_OF_MONTH);
    }

    // 获取小时
    public static int GetHour(Date date) {
        return GetCalendar(date).get(Calendar.HOUR_OF_DAY); // Calendar.HOUR是12小时制度
    }

    // 获取分钟
    public static int GetMinute(Date date) {
        return GetCalendar(date).get(Calendar.MINUTE);
    }

    private static Calendar GetCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static String DateToStr(Date date, String formatStr) {

        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(date);
    }

    public static String DateToStr(Date date) {
        if (date == null)
            return "NULL";
        return DateToStr(date, "yyyy-MM-dd HH:mm:ss.SSS");
    }

    public static String DateToDayStr(Date date) {
        return DateToStr(date, "yyyy-MM-dd");
    }

    /**
     * 字符串转换成日期
     *
     * @param
     * @return date
     */
    public static Date StrToDate(String timeStr) {
        return StrToDate(timeStr, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date StrToDate(String timeStr, String formatStr) {

        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = format.parse(timeStr);
        } catch (ParseException e) {
        }
        return date;
    }

    public static Date StrToDayDate(String timeStr) {
        return StrToDate(timeStr, "yyyy-MM-dd");
    }

    /**
     * 获取两个时间差（天数）
     *
     * @param begin 起始时间
     * @param end   结束时间
     * @return
     */
    public static double GetDifDays(Date begin, Date end) {
        return GetDifSeconds(begin, end) / 60 / 60 / 24;
    }

    /**
     * 获取两个时间差（小时）
     *
     * @param begin 起始时间
     * @param end   结束时间
     * @return
     */
    public static double GetDifHours(Date begin, Date end) {
        return GetDifSeconds(begin, end) / 60 / 60;
    }

    /**
     * 获取两个时间差（分钟）
     *
     * @param begin 起始时间
     * @param end   结束时间
     * @return
     */
    public static double GetDifMinutes(Date begin, Date end) {
        return GetDifSeconds(begin, end) / 60;
    }

    /**
     * 获取两个时间差（秒）
     *
     * @param begin 起始时间
     * @param end   结束时间
     * @return
     */
    public static double GetDifSeconds(Date begin, Date end) {
        long between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
        return ((double) between) / 1000;
    }

    public static void Sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }

    public static Date GetLatestTime(Date date, int interval) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int minute = calendar.get(Calendar.MINUTE) / interval * interval;
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();

    }

    public static boolean IsDateHoursEquals(Date dt1, Date dt2) {

        Calendar cal1 = GetCalendar(dt1);
        int year1 = cal1.get(Calendar.YEAR);
        int month1 = cal1.get(Calendar.MONTH);
        int day1 = cal1.get(Calendar.DAY_OF_MONTH);
        int hour1 = cal1.get(Calendar.HOUR_OF_DAY);

        Calendar cal2 = GetCalendar(dt2);
        int year2 = cal2.get(Calendar.YEAR);
        int month2 = cal2.get(Calendar.MONTH);
        int day2 = cal2.get(Calendar.DAY_OF_MONTH);
        int hour2 = cal2.get(Calendar.HOUR_OF_DAY);

        if (year1 == year2 && month1 == month2 && day1 == day2 && hour1 == hour2)
            return true;
        return false;
    }

    public static Long getUnixMS(Date date) {
        return date.getTime();
    }

    public static Integer getDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        if (weekday == 1) {
            weekday = 7;
        } else {
            weekday--;
        }
        return weekday;
    }

    public static Boolean isTheSameDay(Date date1, Date date2) {
        if (GetYear(date1) == GetYear(date2) &&
                GetDayOfYear(date1) == GetDayOfYear(date2)) {
            return true;
        }
        return false;
    }
}
