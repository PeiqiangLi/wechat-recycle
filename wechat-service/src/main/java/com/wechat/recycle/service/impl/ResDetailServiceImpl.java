package com.wechat.recycle.service.impl;

import com.wechat.recycle.entity.ResDetail;
import com.wechat.recycle.mapper.ResDetailMapper;
import com.wechat.recycle.service.ResDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResDetailServiceImpl implements ResDetailService {

    @Resource
    ResDetailMapper resDetailMapper;

    @Override
    public ResDetail selectOne(Integer id) {
        return null;
    }

    @Override
    public List<ResDetail> selectByType(Integer typeId) {
        return resDetailMapper.selectByType(typeId);
    }

    @Override
    public List<ResDetail> selectByAll() {
        return resDetailMapper.selectByAll();
    }

}
