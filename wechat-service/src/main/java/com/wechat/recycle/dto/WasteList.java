package com.wechat.recycle.dto;

import com.wechat.recycle.entity.OrderType;

import java.util.List;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 16:37 2019/4/24
 * @Modify By:
 */
public class WasteList {

    private List<OrderType> wasteList;

    private Integer orderId;

    private Double price;

    public List<OrderType> getWasteList() {
        return wasteList;
    }

    public void setWasteList(List<OrderType> wasteList) {
        this.wasteList = wasteList;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
