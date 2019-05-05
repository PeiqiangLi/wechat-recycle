package com.wechat.recycle.common.utils;

import java.math.BigDecimal;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 10:49 2019/5/5
 * @Modify By:
 */
public final class CalculateUtil {

    private CalculateUtil() {

    }

    //默认除法运算精度
    private static final int DEFAULT_DIV_SCALE = 10;

    /**
     * @Author: PeiqiangLi
     * @Description: 加法 v1 + v2
     * @param: 
     * @Date: 2019/5/5 10:50
     */
    public static double add(double var1, double var2) {
        BigDecimal b1 = new BigDecimal(Double.toString(var1));
        BigDecimal b2 = new BigDecimal(Double.toString(var2));
        return b1.add(b2).doubleValue();

    }
    
    /**
     * @Author: PeiqiangLi
     * @Description: 减法 v1 - v2
     * @param: 
     * @Date: 2019/5/5 10:50
     */
    public static double subtract(double var1, double var2) {
        BigDecimal b1 = new BigDecimal(Double.toString(var1));
        BigDecimal b2 = new BigDecimal(Double.toString(var2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * @Author: PeiqiangLi
     * @Description: 乘法 v1 * v2
     * @param:
     * @Date: 2019/5/5 10:51
     */
    public static double multiply(double var1, double var2) {
        BigDecimal b1 = new BigDecimal(Double.toString(var1));
        BigDecimal b2 = new BigDecimal(Double.toString(var2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * @Author: PeiqiangLi
     * @Description: 乘法 v1 * v2 String类型参数
     * @param:
     * @Date: 2019/5/5 10:51
     */
    public static String  multiply(String  var1, String  var2) {
        BigDecimal b1 = new BigDecimal(var1);
        BigDecimal b2 = new BigDecimal(var2);
        return b1.multiply(b2).toString();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入,舍入模式采用ROUND_HALF_EVEN
     * @param v1
     * @param v2
     * @return 两个参数的商
     */
    public static double divide(double v1, double v2) {
        return divide(v1, v2, DEFAULT_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。舍入模式采用ROUND_HALF_EVEN
     * @param v1
     * @param v2
     * @param scale 表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double divide(double v1,double v2, int scale) {
        return divide(v1, v2, scale, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。舍入模式采用用户指定舍入模式
     * @param v1
     * @param v2
     * @param scale 表示需要精确到小数点以后几位
     * @param round_mode 表示用户指定的舍入模式
     * @return 两个参数的商
     */
    public static double divide(double v1,double v2,int scale, int round_mode) {
        if(scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, round_mode).doubleValue();
    }

    /**
     * @Author: PeiqiangLi
     * @Description: 除法(一般使用这种方法计算)
     * @param:
     * @Date: 2019/5/5 10:52
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {

            throw new IllegalArgumentException("The scale must be a positive integer or zero");

        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    /**
     * 四舍五入
     * @param v
     * @param scale 精确位数
     * @return
     */
    public static double round(double v, int scale) {

        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }

        BigDecimal b = new BigDecimal(Double.toString(v));

        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    /**
     * 提供精确的小数位四舍五入处理
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @param round_mode 指定的舍入模式
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale, int round_mode)
    {
        if(scale<0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        return b.setScale(scale, round_mode).doubleValue();
    }

}
