package com.wechat.recycle.service;

import com.github.pagehelper.PageInfo;
import com.wechat.recycle.entity.Message;

import java.util.List;

public interface MessageService {

    Message selectOne(Integer id);

    PageInfo<Message> selectAll(Integer pageNum, Integer pageSize, String title);

    int deleteOne(Integer id);

    int addMessage(Message message);

    List<Message> selectCurrent();

}
