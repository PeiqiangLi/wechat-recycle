package com.wechat.recycle.service;

import com.github.pagehelper.PageInfo;
import com.wechat.recycle.entity.Admin;
import com.wechat.recycle.entity.User;

public interface UserService {

    /**
     * 查询一条用户信息
     * @param id
     * @return
     */
    User selectOne(Integer id);

    User selectByOpenid(String openid);

    PageInfo<User> selectAllUser(Integer pageNum, Integer pageSize, String nickName);

    int addUser(User user);

    int updateUser(User user);

    int updateUserAddress(Integer addressId, String openId);

    Admin selectAdmin(String mobile);

    Admin selectUsername(String username);

    int updateAdmin(Admin admin);

    int addAdmin(Admin admin);

    int deleteOne(Integer id);

    PageInfo<Admin> selectAllAdmin(Integer pageNum, Integer pageSize, String username);

}
