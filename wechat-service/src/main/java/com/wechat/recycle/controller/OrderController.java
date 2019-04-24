package com.wechat.recycle.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.wechat.recycle.common.utils.*;
import com.wechat.recycle.dto.OrderDTO;
import com.wechat.recycle.dto.WasteList;
import com.wechat.recycle.entity.Account;
import com.wechat.recycle.entity.Cart;
import com.wechat.recycle.entity.Order;
import com.wechat.recycle.entity.OrderType;
import com.wechat.recycle.service.AccountService;
import com.wechat.recycle.service.CartService;
import com.wechat.recycle.service.OrderService;
import com.wechat.recycle.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private UserService userService;

    @Resource
    private OrderService orderService;

    @Resource
    private CartService cartService;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private AccountService accountService;

    @RequestMapping(value = "/getOrderPage", method = RequestMethod.GET)
    public Result getOrderPage(Integer pageNum, Integer pageSize, String sessionId) {
        if (!redisUtil.hasKey(sessionId)) {
            return ResultUtil.error(StatusCodeEnum.USER_UNLOGIN);
        }
        JSONObject jsonObject = JSONObject.parseObject(redisUtil.get(sessionId).toString());
        String openId =  jsonObject.getString("openId");
        PageInfo<Order> orders = orderService.selectOrders(pageNum, pageSize, openId);
        return ResultUtil.pageResult(orders);
    }

    @RequestMapping(value = "/getOrder", method = RequestMethod.GET)
    public Result getOrder(Integer orderId) {
        OrderDTO orderDTO = orderService.selectOne(orderId);
        return ResultUtil.success(orderDTO);
    }

    /**
     * @Author: PeiqiangLi
     * @Description: 获取订单废品
     * @param:
     * @Date: 2019/4/24 13:37
     */
    @RequestMapping(value = "/getOrderWaste", method = RequestMethod.GET)
    public Result getOrderWaste(Integer orderId) {
        OrderDTO orderDTO = orderService.selectOne(orderId);
        return ResultUtil.success(orderDTO.getOrderTypeDTOS());
    }

    /**
     * @Author: PeiqiangLi
     * @Description: 结束订单
     * @param:
     * @Date: 2019/4/24 13:36
     */
    @RequestMapping(value = "/endOrder", method = RequestMethod.POST)
    public Result endOrder(@RequestBody WasteList wasteList, @RequestHeader String sessionId) {
        if (!redisUtil.hasKey(sessionId)) {
            return ResultUtil.error(StatusCodeEnum.USER_UNLOGIN);
        }
        JSONObject jsonObject = JSONObject.parseObject(redisUtil.get(sessionId).toString());
        String openId =  jsonObject.getString("openId");

        Account account = accountService.selectOne(openId);
        // 更新订单金额、状态
        orderService.updateOrder(wasteList.getOrderId(), wasteList.getPrice());
        // 更新废品数量
        orderService.updateWaste(wasteList.getWasteList());
        account.setAccount(account.getAccount() + wasteList.getPrice());
        account.setSurplus(account.getAccount() - account.getExtract());
        accountService.updateAccount(account);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/getOrderId", method = RequestMethod.GET)
    public Result getOrderId(String sessionId) {
        if (!redisUtil.hasKey(sessionId)) {
            return ResultUtil.error(StatusCodeEnum.USER_UNLOGIN);
        }
        JSONObject jsonObject = JSONObject.parseObject(redisUtil.get(sessionId).toString());
        String openId =  jsonObject.getString("openId");
        int id = userService.selectByOpenid(openId).getId();
        String orderId = DateFormatUtil.getDateTime();
        orderId = orderId + id;
        return ResultUtil.success(orderId);
    }

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public Result addOrder(@RequestBody OrderDTO order, @RequestHeader String sessionId) {
        if (!redisUtil.hasKey(sessionId)) {
            return ResultUtil.error(StatusCodeEnum.USER_UNLOGIN);
        }
        JSONObject jsonObject = JSONObject.parseObject(redisUtil.get(sessionId).toString());
        String openId =  jsonObject.getString("openId");
        int shu = 0;
        order.setCitId(openId);
        if (StringUtils.isEmpty(order.getRemarks())) {
            order.setRemarks("无");
        }
        if (orderService.addOrder(order) > 0) {
            // 删除购物车
            int count = cartService.deleteSome(order.getOrderCart());
            if (count > 0) {
                for (Cart cart : order.getOrderCart()) {
                    OrderType orderType = new OrderType();
                    orderType.setCount(cart.getCount());
                    orderType.setWasteId(cart.getWasteId());
                    orderType.setOrderId(order.getOrderId());
                    shu = orderService.addOrderType(orderType);
                }
            } else {
                return ResultUtil.error("1002","新增订单失败");
            }
        }

        if (shu == 0) {
            return ResultUtil.error("1002","新增订单失败");
        }

        return ResultUtil.success();
    }

    @RequestMapping(value = "/deleteOrder", method = RequestMethod.GET)
    public Result deleteOrder(Integer orderId) {
        int count = orderService.deleteOne(orderId);
        if (count > 0) {
            return ResultUtil.success();
        }
        return ResultUtil.error(StatusCodeEnum.FAILED);
    }

    /**
     * @Author: PeiqiangLi
     * @Description: 查看附近的订单
     * @param:
     * @Date: 2019/4/24 13:26
     */
    @RequestMapping(value = "/getMinOrders", method = RequestMethod.GET)
    public Result getMinOrders(Integer pageNum, Integer pageSize, Double latitude, Double longitude, String province, String city, String district) {
        if (latitude == null || longitude == null) {
            return ResultUtil.error("1003","经纬度为空！");
        }
        PageInfo<OrderDTO> orders = orderService.getMinOrders(pageNum, pageSize, latitude, longitude, province, city, district);
        return ResultUtil.pageResult(orders);
    }

    /**
     * @Author: PeiqiangLi
     * @Description: 订单列表
     * @param:
     * @Date: 2019/4/24 13:27
     */
    public Result getPages(Integer pageNum, Integer pageSize, String province, String city, String district) {
        PageInfo<OrderDTO> orders = orderService.getOrders(pageNum, pageSize, province, city, district);
        return ResultUtil.pageResult(orders);
    }

}
