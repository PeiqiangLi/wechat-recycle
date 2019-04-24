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

    @Override
    public int updateOrder(Integer id, Double price) {
        return orderMapper.updateOrder(id,price);
    }

    @Override
    public void updateWaste(List<OrderType> typeList) {
        for (OrderType orderType : typeList) {
            orderMapper.updateType(orderType);
        }
    }

    @Override
    public PageInfo<OrderDTO> getMinOrders(Integer pageNum, Integer pageSize, Double latitude, Double longitude, String province, String city, String area) {
        PageHelper.startPage(pageNum,pageSize);
        List<OrderDTO> orders = orderMapper.getMinOrders(latitude,longitude,province,city,area);
        return new PageInfo<>(orders);
    }

    @Override
    public PageInfo<OrderDTO> getOrders(Integer pageNum, Integer pageSize, String province, String city, String area) {
        PageHelper.startPage(pageNum,pageSize);
        List<OrderDTO> orders = orderMapper.getOrders(province,city,area);
        PageInfo<OrderDTO> orderDTOPageInfo = new PageInfo<>(orders);
        return orderDTOPageInfo;
    }

}
