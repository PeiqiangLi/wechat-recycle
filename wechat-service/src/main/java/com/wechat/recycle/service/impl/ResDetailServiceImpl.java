package com.wechat.recycle.service.impl;

import com.wechat.recycle.entity.ResDetail;
import com.wechat.recycle.mapper.ResDetailMapper;
import com.wechat.recycle.service.ResDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ResDetailServiceImpl implements ResDetailService {

    @Resource
    ResDetailMapper resDetailMapper;

    @Override
    public ResDetail selectOne(Integer id) {
        return null;
    }
}
