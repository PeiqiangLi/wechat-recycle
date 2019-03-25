package com.wechat.recycle.controller;

import com.github.pagehelper.PageInfo;
import com.wechat.recycle.common.utils.Result;
import com.wechat.recycle.common.utils.ResultUtil;
import com.wechat.recycle.common.utils.StatusCodeEnum;
import com.wechat.recycle.entity.Message;
import com.wechat.recycle.service.MessageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    @RequestMapping(value = "/getCurrentMessage", method = RequestMethod.GET)
    public Result getCurrentMessage() {
        return ResultUtil.success(messageService.selectCurrent());
    }

    @RequestMapping(value = "/getAllMessage", method = RequestMethod.GET)
    public Result getAllMessage(Integer pageNum, Integer pageSize, String title) {
        PageInfo<Message> messagePageInfo = messageService.selectAll(pageNum, pageSize, title);
        return ResultUtil.pageResult(messagePageInfo);
    }

    @RequestMapping(value = "/deleteMessage", method = RequestMethod.GET)
    public Result deleteAddress(Integer id) {
        if (id == null) {
            return ResultUtil.error(StatusCodeEnum.PARAMS_EXCEPTION);
        }
        int count = messageService.deleteOne(id);
        if (count <= 0) return ResultUtil.error("1002","删除消息失败");
        return ResultUtil.success();
    }

}
