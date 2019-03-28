package com.wechat.recycle.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wechat.recycle.dto.UserDTO;
import com.wechat.recycle.entity.Admin;
import com.wechat.recycle.entity.User;
import com.wechat.recycle.mapper.UserMapper;
import com.wechat.recycle.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public PageInfo<UserDTO> selectAllUser(Integer pageNum, Integer pageSize, String nickName, String roleType) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserDTO> users = userMapper.selectAllUser(nickName,roleType);
        return new PageInfo<>(users);
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

    @Override
    public Admin selectAdmin(String mobile) {
        return userMapper.selectAdmin(mobile);
    }

    @Override
    public Admin selectUsername(String username) {
        return userMapper.selectUsername(username);
    }

    @Override
    public int updateAdmin(Admin admin) {
        return userMapper.updateAdmin(admin);
    }

    @Override
    public int addAdmin(Admin admin) {
        return userMapper.addAdmin(admin);
    }

    @Override
    public int deleteOne(Integer id) {
        return userMapper.deleteOne(id);
    }

    @Override
    public PageInfo<Admin> selectAllAdmin(Integer pageNum, Integer pageSize, String username) {
        PageHelper.startPage(pageNum,pageSize);
        List<Admin> adminList = userMapper.selectAllAdmin(username);
        return new PageInfo<>(adminList);
    }

}
