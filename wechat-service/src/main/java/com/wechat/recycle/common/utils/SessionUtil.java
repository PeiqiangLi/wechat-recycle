package com.wechat.recycle.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.wechat.recycle.common.httpclient.HttpUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 16:04 2019/3/5
 * @Modify By:
 */
public final class SessionUtil {

    private SessionUtil() {

    }

    @Resource
    private static RedisUtil redisUtil;

    /**
     * @Author: PeiqiangLi
     * @Description: 请求微信服务器获取SessionKey
     * @param:
     * @Date: 2019/3/5 16:08
     */
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

    /**
     * @Author: PeiqiangLi
     * @Description: 维持登录态
     * @param:
     * @Date: 2019/3/5 16:09
     */
    public static String getSessionInfo(String sessionId, String handleKey) {
        if (redisUtil.hasKey(sessionId)) {
            JSONObject jsonObject = JSONObject.parseObject(redisUtil.get(sessionId).toString());
            if (handleKey == "openId") {
                return jsonObject.getString("openId");
            }
            if (handleKey == "sessionKey") {
                return jsonObject.getString("openId");
            }
            if (handleKey == "unionId") {
                return jsonObject.getString("openId");
            }
        }

        return null;
    }

}
