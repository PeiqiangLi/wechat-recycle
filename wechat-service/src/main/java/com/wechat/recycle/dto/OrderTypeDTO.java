package com.wechat.recycle.dto;

import com.wechat.recycle.entity.OrderType;

public class OrderTypeDTO extends OrderType {

    private static final long serialVersionUID = 1L;

    private String name;

    private String unit;

    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
