package com.wechat.recycle.controller;

import com.alibaba.fastjson.JSONObject;
import com.wechat.recycle.common.utils.*;
import com.wechat.recycle.entity.User;
import com.wechat.recycle.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public Result getUserInfo(@RequestBody User user, @RequestHeader String sessionId) {
        // 从redis取出openId
        JSONObject jsonObject = JSONObject.parseObject(redisUtil.get(sessionId).toString());
        if (user.getNickName() == null || user.getAvatarUrl() == null || jsonObject == null){
            return ResultUtil.error("1003", "获取授权新用户失败");
        }
        user.setOpenId(jsonObject.getString("openId"));

//        if (user.getUnionId() == null) {
//            // 初次登录的情况
//            String result = WXCore.decrypt(appId, encryptedData, jsonObject.getString("sessionKey"), iv);
//            if (result == "") {
//                return ResultUtil.error("1003", "解密用户数据失败");
//            }
//            JSONObject json = JSONObject.parseObject(result);
//            System.out.println(json);
//        }

        // 若用户已经存在数据库中
        if (userService.selectByOpenid(jsonObject.getString("openId")) != null) {
            userService.updateUser(user);
        } else {
            userService.addUser(user);
        }
        // 通过判断用户信息是否是null来证明用户有效性
        return ResultUtil.success();
    }

    @RequestMapping(value = "/getRecycler", method = RequestMethod.POST)
    public Result getRecycler(@RequestBody User user, @RequestHeader String sessionId) {
        // 从redis取出openId
        JSONObject jsonObject = JSONObject.parseObject(redisUtil.get(sessionId).toString());
        if (user.getNickName() == null || user.getAvatarUrl() == null || jsonObject == null){
            return ResultUtil.error("1003", "获取授权新用户失败");
        }
        user.setOpenId(jsonObject.getString("openId"));

        // 若用户已经存在数据库中
        if (userService.selectByOpenid(jsonObject.getString("openId")) != null) {
            return ResultUtil.success();
        }
        //未审核回收员,添加到后台审核
        userService.addUser(user);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Result login(String code, String sessionId) {
        //用户不存在或用户登录已经失效
        if (StringUtils.isEmpty(sessionId) || redisUtil.get(sessionId) == null){
            //重新获取openid,sessionKey
            JSONObject json = SessionUtil.getSessionKey(code);
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
            // String unionId = json.getString("unionid");
            // 生成新的sessionId
            sessionId = UUID.randomUUID().toString();
            // 将openid，sessionKey
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("openId",openId);
            jsonObject.put("sessionKey",sessionKey);
            //将json对象存入redis
            redisUtil.set(sessionId,jsonObject,86400);
            return ResultUtil.success(sessionId);
        }
        // 刷新用户sessionId过期时间
        redisUtil.expire(sessionId,86400);
        // 用户已存在直接返回sessionId
        return ResultUtil.success(sessionId);
    }

    @RequestMapping(value = "/uploadImg")
    public Result uploadImg(MultipartFile file, String path) throws IOException {
        String savePath = FileHandleUtil.upload(file.getInputStream(), path, file.getOriginalFilename());
        System.out.println("文件存放路径为" + savePath);
        return ResultUtil.success("http://localhost:8088/ziyuan" + savePath);
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public Result index() {
        Map<String, String> map = new HashMap<>();
        map.put("aa","nihao");
        map.put("bb", "hello world");
        return ResultUtil.success("hello world");
    }

}