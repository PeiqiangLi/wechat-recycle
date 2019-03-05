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
        return addressMapper.selectOne(id);
    }

    @Override
    public int addAddress(Address address) {
        return addressMapper.addAddress(address);
    }

    @Override
    public int updateAddress(Address address) {
        return addressMapper.updateAddress(address);
    }

    @Override
    public int deleteOne(Integer id) {
        return addressMapper.deleteOne(id);
    }
}
