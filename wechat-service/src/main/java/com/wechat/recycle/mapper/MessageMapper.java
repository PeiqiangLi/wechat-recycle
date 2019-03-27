package com.wechat.recycle.mapper;

import com.wechat.recycle.entity.Message;

import java.util.List;

public interface MessageMapper {

    Message selectOne(Integer id);

    int addMessage(Message message);

    List<Message> selectAll(String title);

    int deleteOne(Integer id);

    List<Message> selectCurrent();

}
