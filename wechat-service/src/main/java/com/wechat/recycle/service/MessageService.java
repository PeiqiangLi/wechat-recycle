package com.wechat.recycle.service;

import com.wechat.recycle.entity.Message;

import java.util.List;

public interface MessageService {

    Message selectOne(Integer id);

    List<Message> selectAll();

    int deleteOne(Integer id);

    int addAddress(Message message);

    List<Message> selectCurrent();

}
