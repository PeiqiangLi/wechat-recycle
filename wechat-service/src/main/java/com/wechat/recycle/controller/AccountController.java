package com.wechat.recycle.controller;

import com.alibaba.fastjson.JSONObject;
import com.wechat.recycle.common.utils.RedisUtil;
import com.wechat.recycle.common.utils.Result;
import com.wechat.recycle.common.utils.ResultUtil;
import com.wechat.recycle.common.utils.StatusCodeEnum;
import com.wechat.recycle.entity.Account;
import com.wechat.recycle.entity.User;
import com.wechat.recycle.service.AccountService;
import com.wechat.recycle.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 15:18 2019/4/24
 * @Modify By:
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @Resource
    private UserService userService;

    @Resource
    private RedisUtil redisUtil;

//    @RequestMapping(value = "/getAccount", method = RequestMethod.GET)
//    public Result getAccount(String sessionId) {
//        if (!redisUtil.hasKey(sessionId)) {
//            return ResultUtil.error(StatusCodeEnum.USER_UNLOGIN);
//        }
//        JSONObject jsonObject = JSONObject.parseObject(redisUtil.get(sessionId).toString());
//        String openId =  jsonObject.getString("openId");
//        Account account = accountService.selectOne(openId);
//        return ResultUtil.success(account);
//    }

    @RequestMapping(value = "/getAccount", method = RequestMethod.GET)
    public Result getAccount(String sessionId) {
        if (!redisUtil.hasKey(sessionId)) {
            return ResultUtil.error(StatusCodeEnum.USER_UNLOGIN);
        }
        JSONObject jsonObject = JSONObject.parseObject(redisUtil.get(sessionId).toString());
        String openId =  jsonObject.getString("openId");
        Account account = accountService.selectOne(openId);
        return ResultUtil.success(account);
    }

    /**
     * @Author: PeiqiangLi
     * @Description: 回收员订单信息统计
     * @param: 
     * @Date: 2019/4/28 14:00
     */
    @RequestMapping(value = "/getOrderData", method = RequestMethod.GET)
    public Result getOrderData(String sessionId){
        if (!redisUtil.hasKey(sessionId)) {
            return ResultUtil.error(StatusCodeEnum.USER_UNLOGIN);
        }
        JSONObject jsonObject = JSONObject.parseObject(redisUtil.get(sessionId).toString());
        String openId =  jsonObject.getString("openId");
        User user = userService.selectByOpenid(openId);
        if (user == null) {
            return ResultUtil.error("1007","请重新授权小程序");
        }
        if (user.getAddressId() != null && user.getAddressId() != 0){
            int orderCount = accountService.getOrderCount(user.getAddressId());
            Double orderTotal = accountService.getOrderTotal(user.getAddressId());
            if (orderTotal == null) orderTotal = 0.00;
            Map map = new HashMap();
            map.put("orderCount",orderCount);
            map.put("orderTotal",orderTotal);
            return ResultUtil.success(map);
        }
        return ResultUtil.success();
    }

}
