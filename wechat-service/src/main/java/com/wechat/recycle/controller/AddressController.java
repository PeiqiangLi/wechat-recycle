package com.wechat.recycle.controller;

import com.wechat.recycle.common.utils.Result;
import com.wechat.recycle.common.utils.ResultUtil;
import com.wechat.recycle.common.utils.StatusCodeEnum;
import com.wechat.recycle.entity.Address;
import com.wechat.recycle.service.AddressService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public Result addAddress(Address address) {
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
        int count = addressService.addAddress(address);
        if (count <= 0) return ResultUtil.error("1002","新增地址失败");
        return ResultUtil.success();
    }

    @RequestMapping(value = "/updateAddress", method = RequestMethod.POST)
    public Result updateAddress(Address address) {
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

}
