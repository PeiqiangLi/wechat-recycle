package com.wechat.recycle.controller;

import com.alibaba.fastjson.JSONObject;
import com.wechat.recycle.common.utils.*;
import com.wechat.recycle.entity.Address;
import com.wechat.recycle.entity.User;
import com.wechat.recycle.service.AddressService;
import com.wechat.recycle.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 11:23 2019/3/5
 * @Modify By:
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Resource
    private AddressService addressService;

    @Resource
    private UserService userService;

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping(value = "/getAddress")
    public Result getAddress(String sessionId) {
        if (!redisUtil.hasKey(sessionId)) {
            return ResultUtil.error(StatusCodeEnum.USER_UNLOGIN);
        }
        JSONObject jsonObject = JSONObject.parseObject(redisUtil.get(sessionId).toString());
        String openId =  jsonObject.getString("openId");
        User user = userService.selectByOpenid(openId);
        if (user.getAddressId() != null){
            return ResultUtil.success(addressService.selectOne(user.getAddressId()));
        }

        return ResultUtil.success(null);
    }

    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public Result addAddress(@RequestBody Address address, @RequestHeader String sessionId) {
        Result result = parameterTest(address);
        if (result != null){
            return ResultUtil.success(result);
        }
        int id = addressService.addAddress(address);
        if (id <= 0) return ResultUtil.error("1002","新增地址失败");
        User user = new User();
        user.setAddressId(id);
        JSONObject jsonObject = JSONObject.parseObject(redisUtil.get(sessionId).toString());
        String openId =  jsonObject.getString("openId");
        userService.updateUserAddress(id, openId);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/updateAddress", method = RequestMethod.POST)
    public Result updateAddress(@RequestBody Address address) {
        Result result = parameterTest(address);
        if (result != null){
            return ResultUtil.success(result);
        }
        int count = addressService.updateAddress(address);
        if (count <= 0) return ResultUtil.error("1002","更新地址失败");
        return ResultUtil.success();
    }

    @RequestMapping(value = "/deleteAddress", method = RequestMethod.GET)
    public Result deleteAddress(Integer id) {
        if (id == null) {
            return ResultUtil.error(StatusCodeEnum.PARAMS_EXCEPTION);
        }
        int count = addressService.deleteOne(id);
        if (count <= 0) return ResultUtil.error("1002","删除地址失败");
        return ResultUtil.success();
    }

    private Result parameterTest(Address address) {
        if (address.getAddress() == null || address.getAddress() == "") {
            // 要求前端去掉最后面的空格
            return ResultUtil.error("1003","详细地址为空");
        }
        else if (address.getArea() == null || address.getArea() == "") {
            return ResultUtil.error("1003","区县地址为空");
        }
        else if (address.getCity() == null || address.getCity() == "") {
            return ResultUtil.error("1003","城市地址为空");
        }
        else if (address.getProvince() == null || address.getProvince() == "") {
            return ResultUtil.error("1003","省级/直辖市地址为空");
        }
        else if (address.getLatitude() == null || address.getLongitude() == null) {
            return ResultUtil.error("1003","经度/纬度异常");
        }
        else if (address.getRealName() == null || address.getRealName() == "") {
            return ResultUtil.error("1003","联系人为空");
        }
        else if (address.getMobile() == null || address.getMobile() == "") {
            return ResultUtil.error("1003","电话号码为空");
        }
        return null;
    }

}
