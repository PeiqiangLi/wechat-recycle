package com.wechat.recycle.service.impl;

import com.wechat.recycle.entity.Message;
import com.wechat.recycle.mapper.MessageMapper;
import com.wechat.recycle.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    @Override
    public Message selectOne(Integer id) {
        return messageMapper.selectOne(id);
    }

    @Override
    public List<Message> selectAll() {
        return null;
    }

    @Override
    public int deleteOne(Integer id) {
        return messageMapper.deleteOne(id);
    }

    @Override
    public int addAddress(Message message) {
        return messageMapper.addAddress(message);
    }

    @Override
    public List<Message> selectCurrent() {
        return messageMapper.selectCurrent();
    }
}
