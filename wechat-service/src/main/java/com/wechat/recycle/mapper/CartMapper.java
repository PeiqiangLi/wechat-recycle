package com.wechat.recycle.mapper;

import com.wechat.recycle.dto.CartDTO;
import com.wechat.recycle.entity.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {

    List<CartDTO> selectCart(String openId);

    int addToCart(Cart cart);

    int deleteOne(@Param("id")Integer id);

}
