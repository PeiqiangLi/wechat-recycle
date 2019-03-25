package com.wechat.recycle.service;

import com.wechat.recycle.entity.ResType;

import java.util.List;

public interface ResTypeService {

    /**
     * 查询一条类别信息
     * @param id
     * @return
     */
    ResType selectOne(Integer id);

    List<ResType> selectAllTypes();

}
