package com.wechat.recycle.service;

import com.github.pagehelper.PageInfo;
import com.wechat.recycle.dto.OrderDTO;
import com.wechat.recycle.entity.Order;
import com.wechat.recycle.entity.OrderType;

public interface OrderService {

    OrderDTO selectOne(Integer id);

    PageInfo<Order> selectOrders(Integer pageNum, Integer pageSize, String openId);

    int addOrder(Order order);

    int addOrderType(OrderType orderType);

    int deleteOne(Integer id);

}
