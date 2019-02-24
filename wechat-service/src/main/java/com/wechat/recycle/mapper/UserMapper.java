package com.wechat.recycle.mapper;

import com.wechat.recycle.entity.User;
import org.apache.ibatis.annotations.Mapper;

public interface UserMapper {

    /**
     * 查询一条用户信息
     * @param id 用户id
     * @return User
     */
    User selectOne(Integer id);

    /**
     * 根据openid查询
     * @param openid 用户openid
     * @return User
     */
    User selectByOpenid(String openid);

    /**
     * 新增用户
     * @param user 用户信息
     * @return int
     */
    int addUser(User user);

    int updateUser(User user);

}
