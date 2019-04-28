package com.wechat.recycle.service;

import com.wechat.recycle.entity.Account;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 14:51 2019/4/24
 * @Modify By:
 */
public interface AccountService {

    Account selectOne(String openId);

    int updateAccount(Account account);

    int getOrderCount(Integer id);

    double getOrderTotal(Integer id);

}
