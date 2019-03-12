package com.wechat.recycle.service;

import com.wechat.recycle.entity.ResDetail;

import java.util.List;

public interface ResDetailService {

    ResDetail selectOne(Integer id);

    List<ResDetail> selectByType(Integer typeId);

    List<ResDetail> selectByAll();

    List<ResDetail> selectByName(Integer typeId, String name);

    int deleteOne(Integer id);

}
