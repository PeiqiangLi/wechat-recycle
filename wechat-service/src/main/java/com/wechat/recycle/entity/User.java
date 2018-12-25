package com.wechat.recycle.entity;

import java.io.Serializable;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 16:49 2018/12/25
 * @Modify By:
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    Integer id;

    String mobile;

    String email;

    String username;

    String realname;

    String password;

    String iconUrl;

    /*角色权限*/
    Integer roleType;

    Integer delFlag;

}
