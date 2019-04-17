package com.wechat.recycle.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.wechat.recycle.common.utils.RedisUtil;
import com.wechat.recycle.common.utils.Result;
import com.wechat.recycle.common.utils.ResultUtil;
import com.wechat.recycle.common.utils.StatusCodeEnum;
import com.wechat.recycle.entity.Feedback;
import com.wechat.recycle.service.FeedbackService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 15:35 2019/4/17
 * @Modify By:
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Resource
    private FeedbackService feedbackService;

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping(value = "/getFeedbacks", method = RequestMethod.GET)
    public Result getFeedbacks(Integer pageNum, Integer pageSize, String title) {
        PageInfo<Feedback> feedbackPageInfo = feedbackService.selectAll(pageNum, pageSize, title);
        return ResultUtil.pageResult(feedbackPageInfo);
    }

    @RequestMapping(value = "/deleteFeedback", method = RequestMethod.GET)
    public Result deleteFeedback(Integer id) {
        int count = feedbackService.deleteOne(id);
        if (count > 0) {
            return ResultUtil.success();
        }
        return ResultUtil.error(StatusCodeEnum.FAILED);
    }

    @RequestMapping(value = "/addFeedback", method = RequestMethod.POST)
    public Result addFeedback(@RequestBody Feedback feedback, @RequestHeader String sessionId) {
        if (!redisUtil.hasKey(sessionId)) {
            return ResultUtil.error(StatusCodeEnum.USER_UNLOGIN);
        }
        JSONObject jsonObject = JSONObject.parseObject(redisUtil.get(sessionId).toString());
        String openId =  jsonObject.getString("openId");
        if (StringUtils.isEmpty(feedback.getTitle())) {
            // 要求前端去掉最后面的空格
            return ResultUtil.error("1003","反馈标题为空");
        } else if (StringUtils.isEmpty(feedback.getContent())) {
            return ResultUtil.error("1003","反馈内容为空");
        }
        feedback.setOpenId(openId);
        int id = feedbackService.addFeedback(feedback);
        if (id <= 0) {
            return ResultUtil.error("1002","新增反馈失败");
        }
        return ResultUtil.success();
    }

}
