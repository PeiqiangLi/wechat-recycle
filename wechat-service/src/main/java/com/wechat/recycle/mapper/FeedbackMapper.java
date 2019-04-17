package com.wechat.recycle.mapper;

import com.wechat.recycle.entity.Feedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 15:00 2019/4/17
 * @Modify By:
 */
public interface FeedbackMapper {

    int addFeedback(Feedback feedback);

    List<Feedback> selectAll(@Param("str")String str);

    int deleteOne(Integer id);

}
