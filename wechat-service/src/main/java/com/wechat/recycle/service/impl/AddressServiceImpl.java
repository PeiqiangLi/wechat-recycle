package com.wechat.recycle.service.impl;

import com.wechat.recycle.entity.Address;
import com.wechat.recycle.mapper.AddressMapper;
import com.wechat.recycle.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 17:28 2019/3/4
 * @Modify By:
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressMapper addressMapper;


    @Override
    public Address selectOne(Integer id) {
        return null;
    }

    @Override
    public int addAddress(Address address) {
        return 0;
    }

    @Override
    public int updateAddress(Address address) {
        return 0;
    }

    @Override
    public int deleteOne(Integer id) {
        return 0;
    }
}
