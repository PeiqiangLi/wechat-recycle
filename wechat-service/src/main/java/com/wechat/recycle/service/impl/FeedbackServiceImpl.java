package com.wechat.recycle.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wechat.recycle.entity.Feedback;
import com.wechat.recycle.mapper.FeedbackMapper;
import com.wechat.recycle.service.FeedbackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 15:20 2019/4/17
 * @Modify By:
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Resource
    private FeedbackMapper feedbackMapper;

    @Override
    public int addFeedback(Feedback feedback) {
        return feedbackMapper.addFeedback(feedback);
    }

    @Override
    public PageInfo<Feedback> selectAll(Integer pageNum, Integer pageSize, String str) {
        PageHelper.startPage(pageNum,pageSize);
        List<Feedback> feedbackList = feedbackMapper.selectAll(str);
        return new PageInfo<>(feedbackList);
    }

    @Override
    public int deleteOne(Integer id) {
        return feedbackMapper.deleteOne(id);
    }
}
