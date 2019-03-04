package com.wechat.recycle.controller;

import com.wechat.recycle.common.utils.Result;
import com.wechat.recycle.common.utils.ResultUtil;
import com.wechat.recycle.entity.User;
import com.wechat.recycle.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class userController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public Result getUserInfo(User user) {

        return ResultUtil.success();
    }

}
