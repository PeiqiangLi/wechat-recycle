package com.wechat.recycle.controller;

import com.wechat.recycle.entity.User;
import com.wechat.recycle.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/main")
public class IndexController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public User index() {
        return userService.selectOne(1);
    }

}