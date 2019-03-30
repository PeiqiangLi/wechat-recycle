package com.wechat.recycle.service;

import com.github.pagehelper.PageInfo;
import com.wechat.recycle.entity.ResDetail;

import java.util.List;

public interface ResDetailService {

    ResDetail selectOne(Integer id);

    List<ResDetail> selectByType(Integer typeId);

    List<ResDetail> selectByAll();

    List<ResDetail> selectByName(Integer typeId, String name);

    int deleteOne(Integer id);

    int addResDetail(ResDetail resDetail);

    int updateResDetail(ResDetail resDetail);

    PageInfo<ResDetail> selectWastePage(Integer pageNum, Integer pageSize, Integer typeId, String name);

}
