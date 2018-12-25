package com.wechat.recycle.entity;

import java.io.Serializable;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 14:56 2018/12/25
 * @Modify By:
 */
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    Integer id;

    String mobile;

    String email;

    String username;

    String password;

    Integer delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
