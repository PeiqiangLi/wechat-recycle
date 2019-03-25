package com.wechat.recycle.dto;

import com.wechat.recycle.entity.Cart;
import com.wechat.recycle.entity.Order;
import com.wechat.recycle.entity.OrderType;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 16:34 2019/3/11
 * @Modify By:
 */
public class OrderDTO extends Order {

    private static final long serialVersionUID = 1L;

    private double distance;

    private List<Cart> orderCart;

    private List<OrderTypeDTO> orderTypeDTOS;

    private String realName;

    private String mobile;

    private String address;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public List<Cart> getOrderCart() {
        return orderCart;
    }

    public void setOrderCart(List<Cart> orderCart) {
        this.orderCart = orderCart;
    }

    public List<OrderTypeDTO> getOrderTypeDTOS() {
        return orderTypeDTOS;
    }

    public void setOrderTypeDTOS(List<OrderTypeDTO> orderTypeDTOS) {
        this.orderTypeDTOS = orderTypeDTOS;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
