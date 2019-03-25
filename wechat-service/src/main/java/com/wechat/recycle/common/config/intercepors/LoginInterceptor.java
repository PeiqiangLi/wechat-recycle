package com.wechat.recycle.common.config.intercepors;

import com.alibaba.fastjson.JSONObject;
import com.wechat.recycle.common.utils.RedisUtil;
import com.wechat.recycle.common.utils.ResultUtil;
import com.wechat.recycle.common.utils.StatusCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        String sessionId = request.getHeader("sessionId");
        if (redisUtil.hasKey(sessionId)) {
            return true;
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        pw.write(JSONObject.toJSONString(ResultUtil.error(StatusCodeEnum.USER_UNLOGIN)));
        pw.flush();
        pw.close();
        return false;

    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
