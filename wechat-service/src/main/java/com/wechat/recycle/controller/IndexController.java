package com.wechat.recycle.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wechat.recycle.common.httpclient.HttpUtils;
import com.wechat.recycle.common.utils.RedisUtil;
import com.wechat.recycle.common.utils.Result;
import com.wechat.recycle.common.utils.ResultUtil;
import com.wechat.recycle.common.utils.WXCore;
import com.wechat.recycle.entity.User;
import com.wechat.recycle.service.UserService;
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

    private static final String appId = "wxbab581961ef84ef7";

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public Result getUserInfo(String openid) {
        User user = null;
        if (openid != null && redisUtil.get("openid") != null){
            user = userService.selectByOpenid(openid);
        }
        //通过判断用户信息是否是null来证明用户有效性
        return ResultUtil.success(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(String code, String rawData, String signature, String encryptedData, String iv) {
        JSONObject rawDataJson = JSON.parseObject(rawData);
        JSONObject json = getSessionKey(code);
        if (json == null) {
            return ResultUtil.error("1002","获取sessionKey失败");
        }
        if (json.getIntValue("errcode") != 0){
            return ResultUtil.error(String.valueOf(json.getIntValue("errcode")),json.getString("errmsg"));
        }
        //用户唯一标识
        String openid = json.getString("openid");
        //会话密钥
        String sessionKey = json.getString("session_key");
        //用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
        String unionid = json.getString("unionid");

        //解密
        String str = WXCore.decrypt(appId, encryptedData, sessionKey, iv);
        if (str == "") {
            return ResultUtil.error("1002","aes解密失败");
        }
        JSONObject userInfo = JSON.parseObject(str);

        User user = userService.selectByOpenid(openid);
        if (user == null) {
            user.setOpenid(openid);
            user.setUnionid(unionid);
            user.setNickName(rawDataJson.getString("nickName"));
            user.setRealName(rawDataJson.getString(""));
            user.setIconUrl(rawDataJson.getString("avatarUrl"));
        } else {

        }
        redisUtil.set(openid,sessionKey,86400);
        return ResultUtil.success(user);
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public Result index() {
        return ResultUtil.success(userService.selectOne(1));
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public Integer addUser(User user) {

        return 0;
    }

    public static JSONObject getSessionKey(String code) {
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