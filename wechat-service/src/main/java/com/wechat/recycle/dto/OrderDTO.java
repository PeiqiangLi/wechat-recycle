package com.wechat.recycle.dto;

import com.wechat.recycle.entity.Order;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 16:34 2019/3/11
 * @Modify By:
 */
public class OrderDTO extends Order {

    private static final long serialVersionUID = 1L;

    private double distance;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String createdate = sdf.format(date);
        BigInteger k=new BigInteger(String.valueOf(createdate));
        System.out.println(k);
    }

}