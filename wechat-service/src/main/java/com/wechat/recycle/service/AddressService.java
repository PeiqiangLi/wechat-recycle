package com.wechat.recycle.service;

import com.github.pagehelper.PageInfo;
import com.wechat.recycle.entity.Address;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 17:25 2019/3/4
 * @Modify By:
 */
public interface AddressService {

    Address selectOne(Integer id);

    int addAddress(Address address);

    int updateAddress(Address address);

    int deleteOne(Integer id);

    PageInfo<Address> selectAllAddress(Integer pageNum, Integer pageSize, String province,String city,String area, String address);

}
