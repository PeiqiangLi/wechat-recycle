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
        return resDetailMapper.selectOne(id);
    }

    @Override
    public List<ResDetail> selectByType(Integer typeId) {
        return resDetailMapper.selectByType(typeId);
    }

    @Override
    public List<ResDetail> selectByAll() {
        return resDetailMapper.selectByAll();
    }

    @Override
    public List<ResDetail> selectByName(Integer typeId, String name) {
        return resDetailMapper.selectByName(typeId, name);
    }

    @Override
    public int deleteOne(Integer id) {
        return resDetailMapper.deleteOne(id);
    }


}
