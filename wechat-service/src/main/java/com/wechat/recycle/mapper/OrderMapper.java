package com.wechat.recycle.mapper;

import com.wechat.recycle.dto.OrderDTO;
import com.wechat.recycle.dto.OrderTypeDTO;
import com.wechat.recycle.entity.Order;
import com.wechat.recycle.entity.OrderType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 13:52 2019/3/11
 * @Modify By:
 */
public interface OrderMapper {

    OrderDTO selectOne(Integer id);

    List<Order> selectOrders(String openId);

    int addOrder(Order order);

    int addOrderType(OrderType orderType);

    List<OrderTypeDTO> selectTypes(String orderId);

//    int updateOrder(Order order);

    int deleteOne(@Param("id")Integer id);

}
