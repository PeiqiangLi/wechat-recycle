package com.wechat.recycle.mapper;

import com.wechat.recycle.entity.Admin;
import com.wechat.recycle.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    List<User> selectAllUser(String nickName);

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
    int updateUserAddress(@Param("addressId")Integer addressId, @Param("openId")String openId);

    Admin selectAdmin(String mobile);

    int addAdmin(Admin admin);

    int deleteOne(Integer id);

    List<Admin> selectAllAdmin(String username);

}
