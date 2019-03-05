package com.wechat.recycle.service.impl;

import com.wechat.recycle.entity.User;
import com.wechat.recycle.mapper.UserMapper;
import com.wechat.recycle.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User selectOne(Integer id) {
        return userMapper.selectOne(id);
    }

    @Override
    public User selectByOpenid(String openId) {
        return userMapper.selectByOpenid(openId);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int updateUserAddress(Integer addressId, String openId) {
        return userMapper.updateUserAddress(addressId, openId);
    }

}
