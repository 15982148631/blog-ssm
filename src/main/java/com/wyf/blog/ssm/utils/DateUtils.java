package com.wyf.blog.ssm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 时间工具类
 * @Author: 王忆峰
 * @Date: 2020/6/27 13:39
 * @Version: 1.0
 */
public class DateUtils {

    public static String dateToStr(Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }



}
