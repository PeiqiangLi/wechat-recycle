package com.wechat.recycle.dto;

import com.wechat.recycle.entity.Cart;

public class CartDTO extends Cart {

    private static final long serialVersionUID = 1L;

    private String name;

    private Double price;

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
}
