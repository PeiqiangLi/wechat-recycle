package com.wechat.recycle.mapper;

import com.wechat.recycle.entity.Order;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 13:52 2019/3/11
 * @Modify By:
 */
public interface OrderMapper {

    Order selectOne(Integer id);

    int deleteOne(@Param("id")Integer id);

}
