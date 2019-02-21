package com.wechat.recycle.controller;

import com.alibaba.fastjson.JSONObject;
import com.wechat.recycle.common.httpclient.HttpUtils;
import com.wechat.recycle.common.utils.RedisUtil;
import com.wechat.recycle.common.utils.Result;
import com.wechat.recycle.common.utils.ResultUtil;
import com.wechat.recycle.entity.User;
import com.wechat.recycle.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/main")
public class IndexController {

    @Resource
    private UserService userService;

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(String code) {
        Map<String,String> map = new HashMap<>();
        map.put("appid","你的小程序appId");
        map.put("secret","你的小程序appSecret");
        map.put("js_code",code);
        map.put("grant_type","authorization_code");
        try {
            JSONObject json = JSONObject.parseObject(HttpUtils.doGet("https://api.weixin.qq.com/sns/jscode2session",map));
            if (json.getIntValue("errcode") != 0){
                return ResultUtil.error(String.valueOf(json.getIntValue("errcode")),json.getString("errmsg"));
            }
            redisUtil.get("openid");
            //用户唯一标识
            json.getString("openid");
            //会话密钥
            json.getString("session_key");
            //用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
            json.getString("unionid");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultUtil.success();
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public Result index() {
        return ResultUtil.success(userService.selectOne(1));
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public Integer addUser(User user) {

        return 0;
    }

}