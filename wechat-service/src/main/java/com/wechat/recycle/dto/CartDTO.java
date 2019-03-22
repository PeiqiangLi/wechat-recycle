package com.wechat.recycle.dto;

import com.wechat.recycle.entity.Cart;

public class CartDTO extends Cart {

    private static final long serialVersionUID = 1L;

    private String name;

    private Double price;

    private String unit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
