package com.wechat.recycle.service;

import com.github.pagehelper.PageInfo;
import com.wechat.recycle.dto.OrderDTO;
import com.wechat.recycle.entity.Order;
import com.wechat.recycle.entity.OrderType;

import java.util.List;

public interface OrderService {

    OrderDTO selectOne(Integer id);

    PageInfo<Order> selectOrders(Integer pageNum, Integer pageSize, String openId);

    int addOrder(Order order);

    int addOrderType(OrderType orderType);

    int deleteOne(Integer id);

    int updateOrder(Integer id, Double price);

    void updateWaste(List<OrderType> typeList);

    PageInfo<OrderDTO> getMinOrders(Integer pageNum, Integer pageSize, Double latitude, Double longitude, String province, String city, String area);

    PageInfo<OrderDTO> getOrders(Integer pageNum, Integer pageSize, String province, String city, String area);

}
