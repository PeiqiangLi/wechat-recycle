package com.wechat.recycle.mapper;

import com.wechat.recycle.dto.AddressDTO;
import com.wechat.recycle.entity.Address;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 16:42 2019/3/4
 * @Modify By:
 */
public interface AddressMapper {

    Address selectOne(@Param("id")Integer id);

    int addAddress(Address address);

    int updateAddress(Address address);

    int deleteOne(@Param("id")Integer id);

    AddressDTO getMinAddress(@Param("latitude")Double latitude, @Param("longitude")Double longitude);

}
