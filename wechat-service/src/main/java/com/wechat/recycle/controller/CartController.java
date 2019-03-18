package com.wechat.recycle.controller;

import com.alibaba.fastjson.JSONObject;
import com.wechat.recycle.common.utils.RedisUtil;
import com.wechat.recycle.common.utils.Result;
import com.wechat.recycle.common.utils.ResultUtil;
import com.wechat.recycle.common.utils.StatusCodeEnum;
import com.wechat.recycle.dto.CartDTO;
import com.wechat.recycle.entity.Cart;
import com.wechat.recycle.service.CartService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource
    private CartService cartService;

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping(value = "/getCart", method = RequestMethod.GET)
    public Result getCart(String sessionId) {
        if (!redisUtil.hasKey(sessionId)) {
            return ResultUtil.error(StatusCodeEnum.USER_UNLOGIN);
        }
        JSONObject jsonObject = JSONObject.parseObject(redisUtil.get(sessionId).toString());
        String openId =  jsonObject.getString("openId");
        List<CartDTO> cartDTOList = cartService.selectCart(openId);
        return ResultUtil.success(cartDTOList);
    }

    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    public Result addToCart(@RequestBody Cart cart, @RequestHeader String sessionId) {
        if (!redisUtil.hasKey(sessionId)) {
            return ResultUtil.error(StatusCodeEnum.USER_UNLOGIN);
        }
        JSONObject jsonObject = JSONObject.parseObject(redisUtil.get(sessionId).toString());
        String openId =  jsonObject.getString("openId");
        cart.setOpenId(openId);
        if (cart.getWasteId() == null || cart.getWasteId() == 0) {
            return ResultUtil.error("1003","wasteId为空");
        }
        else if (StringUtils.isEmpty(cart.getOpenId())) {
            return ResultUtil.error("1003","openId为空");
        }
        else if (cart.getCount() == null || cart.getCount() == 0) {
            return ResultUtil.error("1003","数量为空");
        }
        else if (cart.getMoney() == null || cart.getMoney() == 0) {
            return ResultUtil.error("1003","金额为空");
        }
        int count = cartService.addToCart(cart);
        if (count <= 0) {
            return ResultUtil.error("1002","添加购物车失败");
        }
        return ResultUtil.success();
    }

    @RequestMapping(value = "/deleteCart", method = RequestMethod.GET)
    public Result deleteCart(Integer id) {
        if (id == null || id ==0) {
            return ResultUtil.error(StatusCodeEnum.PARAMS_EXCEPTION);
        }
        int count = cartService.deleteOne(id);
        if (count <= 0) return ResultUtil.error("1002","删除失败");
        return ResultUtil.success();
    }

}
