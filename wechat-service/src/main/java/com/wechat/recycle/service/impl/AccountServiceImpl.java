package com.wechat.recycle.service.impl;

import com.wechat.recycle.entity.Account;
import com.wechat.recycle.mapper.AccountMapper;
import com.wechat.recycle.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 14:57 2019/4/24
 * @Modify By:
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public Account selectOne(String openId) {
        Account account = accountMapper.selectOne(openId);
        if (account == null) {
            Account account1 = new Account();
            account1.setOpenId(openId);
            account1.setAccount(0.00);
            account1.setSurplus(0.00);
            account1.setExtract(0.00);
            accountMapper.addAccount(openId);
            return account1;
        }
        return account;
    }

    @Override
    public int updateAccount(Account account) {
        return accountMapper.updateAccount(account);
    }

    @Override
    public int getOrderCount(Integer id) {
        return accountMapper.getOrderCount(id);
    }

    @Override
    public Double getOrderTotal(Integer id) {
        return accountMapper.getOrderTotal(id);
    }

}
