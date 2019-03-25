package com.wechat.recycle.service.impl;

import com.wechat.recycle.entity.ResType;
import com.wechat.recycle.mapper.ResTypeMapper;
import com.wechat.recycle.service.ResTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResTypeServiceImpl implements ResTypeService {

    @Resource
    ResTypeMapper resTypeMapper;

    @Override
    public ResType selectOne(Integer id) {
        return null;
    }

    @Override
    public List<ResType> selectAllTypes() {
        return resTypeMapper.selectAllTypes();
    }

}
