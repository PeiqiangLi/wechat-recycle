package com.wechat.recycle.common.utils;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 14:26 2019/3/11
 * @Modify By:
 */
public final class DistanceUtil {

    private DistanceUtil() {

    }

    private static final double EARTH_RADIUS = 6371.393;//地球半径,单位千米

    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }

    /**
     *
     * @param lat1 第一个纬度
     * @param lng1 第一个经度
     * @param lat2 第二个纬度
     * @param lng2 第二个经度
     * @return 两个经纬度的距离
     */
    public static double getDistance(double lat1,double lng1,double lat2,double lng2)
    {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        // 保留2位小数*100
        s = Math.round(s * 100);
        s = s / 100;
        return s;

    }

    public static void main(String[] args)
    {
        System.out.println(DistanceUtil.getDistance(30.323453,120.119421 , 30.334192, 120.162279));
    }

}
