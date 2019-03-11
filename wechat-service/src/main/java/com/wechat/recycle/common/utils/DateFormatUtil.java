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

    public static BigInteger getDateTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String createdate = sdf.format(date);
        return new BigInteger(String.valueOf(createdate));
    }

}
