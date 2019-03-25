package com.wechat.recycle.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wechat.recycle.dto.AddressDTO;
import com.wechat.recycle.dto.OrderDTO;
import com.wechat.recycle.dto.OrderTypeDTO;
import com.wechat.recycle.entity.Address;
import com.wechat.recycle.entity.Order;
import com.wechat.recycle.entity.OrderType;
import com.wechat.recycle.mapper.AddressMapper;
import com.wechat.recycle.mapper.OrderMapper;
import com.wechat.recycle.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private AddressMapper addressMapper;

    @Override
    public OrderDTO selectOne(Integer id) {
        OrderDTO orderDTO = orderMapper.selectOne(id);
        List<OrderTypeDTO> orderTypeDTOS = orderMapper.selectTypes(orderDTO.getOrderId());
        orderDTO.setOrderTypeDTOS(orderTypeDTOS);
        return orderDTO;
    }

    @Override
    public PageInfo<Order> selectOrders(Integer pageNum, Integer pageSize, String openId) {
        PageHelper.startPage(pageNum,pageSize);
        List<Order> orders = orderMapper.selectOrders(openId);
        return new PageInfo<>(orders);
    }

    @Override
    public int addOrder(Order order) {
        Address address = addressMapper.selectOne(order.getAddressId());
        AddressDTO addressDTO = addressMapper.getMinAddress(address.getLatitude(), address.getLongitude());
        // 匹配最近的回收站点
        order.setRecId(addressDTO.getId());
        order.setOrderState(1);
        return orderMapper.addOrder(order);
    }

    @Override
    public int addOrderType(OrderType orderType) {
        return orderMapper.addOrderType(orderType);
    }

    @Override
    public int deleteOne(Integer id) {
        return orderMapper.deleteOne(id);
    }

}
