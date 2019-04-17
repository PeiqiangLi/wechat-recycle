package com.wechat.recycle.service;

import com.github.pagehelper.PageInfo;
import com.wechat.recycle.entity.Feedback;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 15:19 2019/4/17
 * @Modify By:
 */
public interface FeedbackService {

    int addFeedback(Feedback feedback);

    PageInfo<Feedback> selectAll(Integer pageNum, Integer pageSize, String str);

    int deleteOne(Integer id);

}
