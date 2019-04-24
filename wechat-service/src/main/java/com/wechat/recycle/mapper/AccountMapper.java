package com.wechat.recycle.mapper;

import com.wechat.recycle.entity.Account;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 14:02 2019/4/24
 * @Modify By:
 */
public interface AccountMapper {

    Account selectOne(String openId);

    int addAccount(String openId);

    int updateAccount(Account account);

}
