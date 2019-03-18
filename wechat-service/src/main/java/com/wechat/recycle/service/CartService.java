package com.wechat.recycle.service;

import com.wechat.recycle.dto.CartDTO;
import com.wechat.recycle.entity.Cart;

import java.util.List;

public interface CartService {

    List<CartDTO> selectCart(String openId);

    int addToCart(Cart cart);

    int deleteOne(Integer id);

}
