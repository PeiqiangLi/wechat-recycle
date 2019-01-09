package com.wechat.recycle.mapper;

import com.wechat.recycle.entity.ResType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResTypeMapper {

    ResType selectOne(Integer id);

    /**
     * 新增一条物品类别
     * @return
     */
    int addResType(ResType resType);

    int updateResType(ResType resType);

}
