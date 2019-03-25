package com.wechat.recycle.common.utils;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 17:02 2019/3/11
 * @Modify By:
 */
public final class DateFormatUtil {

    private DateFormatUtil() {

    }

    public static String getDateTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

}
