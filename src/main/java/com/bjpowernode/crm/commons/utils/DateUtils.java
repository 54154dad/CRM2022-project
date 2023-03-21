package com.bjpowernode.crm.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Classname DateUtils
 * @Date 2022/12/21
 * @Created by YQ
 */
public class DateUtils {
    public static  String forMateDateTime(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataStr = sf.format(date);
        return dataStr;
    }
}
