package com.wechat.recycle.entity;

import java.io.Serializable;

/**
 * @Author: PeiqiangLi
 * @Description: 用户账户
 * @Date: Created in 13:51 2019/4/24
 * @Modify By:
 */
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String openId;

    /**
     * @Author: PeiqiangLi
     * @Description: 账户总收入
     * @param: 
     * @Date: 2019/4/24 13:55
     */
    private Double account;

    /**
     * @Author: PeiqiangLi
     * @Description: 已提现收入
     * @param: 
     * @Date: 2019/4/24 13:55
     */
    private Double extract;

    /**
     * @Author: PeiqiangLi
     * @Description: 剩余收入
     * @param: 
     * @Date: 2019/4/24 13:56
     */
    private Double surplus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Double getAccount() {
        return account;
    }

    public void setAccount(Double account) {
        this.account = account;
    }

    public Double getExtract() {
        return extract;
    }

    public void setExtract(Double extract) {
        this.extract = extract;
    }

    public Double getSurplus() {
        return surplus;
    }

    public void setSurplus(Double surplus) {
        this.surplus = surplus;
    }
}
