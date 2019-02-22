package com.wechat.recycle.mapper;

import com.wechat.recycle.entity.ResDetail;
import org.apache.ibatis.annotations.Mapper;

public interface ResDetailMapper {

    ResDetail selectOne(Integer id);

    /**
     * 在类别下新增一项物品
     * @return
     */
    int addResDetail(ResDetail resDetail);

    int updateResDetail(ResDetail resDetail);

}
