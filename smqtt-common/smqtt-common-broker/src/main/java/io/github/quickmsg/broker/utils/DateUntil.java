package io.github.quickmsg.broker.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description
 * @Author 孙士桓
 * @Email 13733918655@163.com
 * @Date 2019/12/9 20:45
 * @Version 1.0
 */
public class DateUntil {

    public static Date getCurrentDate() {
        return new Date();
    }

    public static String getDateString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String previousDate = dateFormat.format(date);
        return previousDate;
    }

    public static String getDateStrings(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String previousDate = dateFormat.format(date);
        return previousDate;
    }

    public static Date getDateString(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }

    public static String strToDateFormat(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        formatter.setLenient(false);
        Date newDate = formatter.parse(date);
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(newDate);
    }

    public static String getDateFormatString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String previousDate = dateFormat.format(date);
        return previousDate;
    }

    public static String getDateFormatTostr(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String previousDate = dateFormat.format(date);
        return previousDate;
    }

    public static String getCurrentDateWithZero(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String previousDate = dateFormat.format(date);
        return previousDate;
    }

    public static String getCurrentDateWithNight(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        String previousDate = dateFormat.format(date);
        return previousDate;
    }

    public static String getPreviousDateWithZero(Date date) {
        Date prevDate = new Date(date.getTime() - (24 * 3600000));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String previousDate = dateFormat.format(prevDate);
        return previousDate;
    }

    public static Date getFomatDate(String str) {
        //创建SimpleDateFormat对象实例并定义好转换格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            // 注意格式需要与上面一致，不然会出现异常
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将时间戳转换为日期
     *
     * @param stamp 时间戳
     * @return 时间，返回格式为 yyyy-MM-dd-HH-mm-ss
     */
    public static String Stamp2Date(Long stamp) {
        String result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(stamp);
        return result;
    }

    /**
     * 取得当前时间戳（精确到秒）
     *
     * @return
     */
    public static String timeStamp() {
        long time = System.currentTimeMillis();
        String t = String.valueOf(time / 1000);
        return t;
    }

    /**
     * 取得当前时间戳（精确到秒）Long 类型
     *
     * @return
     */
    public static Long timeStampL() {
        long time = System.currentTimeMillis();
        return time / 1000;
    }

    /**
     * 获取指定url中的某个参数
     *
     * @param url
     * @param name
     * @return
     */
    public static String getParamByUrl(String url, String name) {
        url += "&";
        String pattern = "(\\?|&){1}#{0,1}" + name + "=[a-zA-Z0-9]*(&{1})";

        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(url);
        if (m.find()) {
            System.out.println(m.group(0));
            return m.group(0).split("=")[1].replace("&", "");
        } else {
            return null;
        }
    }


}
