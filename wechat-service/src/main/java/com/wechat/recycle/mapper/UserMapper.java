package com.wechat.recycle.mapper;

import com.wechat.recycle.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * 查询一条用户信息
     * @param id
     * @return
     */
    User selectOne(Integer id);

    /**
     * 新增用户
     * @param user
     * @return
     */
    int addUser(User user);

    int updateUser(User user);

}
