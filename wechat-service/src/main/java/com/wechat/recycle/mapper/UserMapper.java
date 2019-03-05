package com.wechat.recycle.mapper;

import com.wechat.recycle.entity.User;

public interface UserMapper {

    /**
     * 查询一条用户信息
     * @param id 用户id
     * @return User
     */
    User selectOne(Integer id);

    /**
     * 根据openId查询
     * @param openId 用户openId
     * @return User
     */
    User selectByOpenid(String openId);

    /**
     * 新增用户
     * @param user 用户信息
     * @return int
     */
    int addUser(User user);

    /**
     * 更新用户
     * @param user 用户信息
     * @return int
     */
    int updateUser(User user);

    /**
     * @Author: PeiqiangLi
     * @Description: 更新用户地址id
     * @param:
     * @Date: 2019/3/5 15:13
     */
    int updateUserAddress(Integer addressId, String openId);

}
