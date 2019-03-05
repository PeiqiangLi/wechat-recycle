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

    @RequestMapping(value = "/changeUserRole", method = RequestMethod.GET)
    public Result changeUserRole(String openId, String roleType) {
        User user = new User();
        user.setOpenId(openId);
        user.setRoleType(roleType);
        int count = userService.updateUser(user);
        if (count <= 0) return ResultUtil.error("1002","修改用户类别失败");
        return ResultUtil.success();
    }

}
