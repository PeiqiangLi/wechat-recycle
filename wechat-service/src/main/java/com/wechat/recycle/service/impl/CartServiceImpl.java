package com.wechat.recycle.service.impl;

import com.wechat.recycle.dto.CartDTO;
import com.wechat.recycle.entity.Cart;
import com.wechat.recycle.mapper.CartMapper;
import com.wechat.recycle.service.CartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Resource
    private CartMapper cartMapper;

    @Override
    public List<CartDTO> selectCart(String openId) {
        return cartMapper.selectCart(openId);
    }

    @Override
    public int addToCart(Cart cart) {
        return cartMapper.addToCart(cart);
    }

    @Override
    public int deleteOne(Integer id) {
        return cartMapper.deleteOne(id);
    }

    @Override
    public int deleteSome(List ids) {
        return cartMapper.deleteSome(ids);
    }

}
