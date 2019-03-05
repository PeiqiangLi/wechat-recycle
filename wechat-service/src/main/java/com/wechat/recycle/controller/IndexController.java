package com.wechat.recycle.controller;

import com.alibaba.fastjson.JSONObject;
import com.wechat.recycle.common.httpclient.HttpUtils;
import com.wechat.recycle.common.utils.RedisUtil;
import com.wechat.recycle.common.utils.Result;
import com.wechat.recycle.common.utils.ResultUtil;
import com.wechat.recycle.entity.User;
import com.wechat.recycle.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/main")
public class IndexController {

    @Resource
    private UserService userService;

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public Result getUserInfo(User user, String encryptedData, String sessionId) {
        // 从redis取出openId，unionId
        JSONObject jsonObject = JSONObject.parseObject(redisUtil.get(sessionId).toString());
        if (user.getNickName() == null || user.getAvatarUrl() == null || jsonObject == null){
            return ResultUtil.error("1003", "获取授权新用户失败");
        }
        user.setOpenId(jsonObject.getString("openId"));
        user.setUnionId(jsonObject.getString("unionId"));
        // 若用户已经存在数据库中
        if (userService.selectByOpenid(jsonObject.getString("openId")) != null) {
            userService.updateUser(user);
        } else {
            userService.addUser(user);
        }
        // 通过判断用户信息是否是null来证明用户有效性
        return ResultUtil.success();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Result login(String code, String sessionId) {
        //用户不存在或用户登录已经失效
        if (sessionId == null || redisUtil.get(sessionId) == null){
            //重新获取openid,sessionKey
            JSONObject json = getSessionKey(code);
            if (json == null) {
                return ResultUtil.error("1002","获取sessionKey失败");
            }
            if (json.getIntValue("errcode") != 0){
                return ResultUtil.error(String.valueOf(json.getIntValue("errcode")),json.getString("errmsg"));
            }
            // 用户唯一标识
            String openId = json.getString("openid");
            // 会话密钥
            String sessionKey = json.getString("session_key");
            // 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
            String unionId = json.getString("unionid");
            // 生成新的sessionId
            sessionId = UUID.randomUUID().toString();
            // 将openid，sessionKey，unionid放入json对象
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("openId",openId);
            jsonObject.put("sessionKey",sessionKey);
            jsonObject.put("unionId",unionId);
            //将json对象存入redis
            redisUtil.set(sessionId,jsonObject,86400);
            return ResultUtil.success(sessionId);
        }
        // 刷新用户sessionId过期时间
        redisUtil.expire(sessionId,86400);
        // 用户已存在直接返回sessionId
        return ResultUtil.success(sessionId);
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public Result index() {
        return ResultUtil.success("hello world");
    }

    private static JSONObject getSessionKey(String code) {
        Map<String,String> map = new HashMap<>();
        map.put("appid","wxbab581961ef84ef7");
        map.put("secret","cc17ccc501c94fc2e797c245a0dee29d");
        map.put("js_code",code);
        map.put("grant_type","authorization_code");
        try {
            JSONObject json = JSONObject.parseObject(HttpUtils.doGet("https://api.weixin.qq.com/sns/jscode2session",map));
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}