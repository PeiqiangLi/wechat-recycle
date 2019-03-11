package com.wechat.recycle.dto;

import com.wechat.recycle.entity.Address;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 16:25 2019/3/4
 * @Modify By:
 */
public class AddressDTO extends Address {

    private static final long serialVersionUID = 1L;

    private double distance;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
