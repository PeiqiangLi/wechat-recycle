package com.wechat.recycle.entity;

import java.io.Serializable;

public class OrderType implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String orderId;

    private Integer wasteId;

    private Double count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getWasteId() {
        return wasteId;
    }

    public void setWasteId(Integer wasteId) {
        this.wasteId = wasteId;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }
}
