package com.wechat.recycle.service;

import com.wechat.recycle.entity.ResType;

public interface ResTypeService {

    /**
     * 查询一条类别信息
     * @param id
     * @return
     */
    ResType selectOne(Integer id);

}
