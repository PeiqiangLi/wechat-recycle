package com.wechat.recycle.mapper;

import com.wechat.recycle.entity.ResType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface ResTypeMapper {

    ResType selectOne(Integer id);

    List<ResType> selectAllTypes();

    /**
     * 新增一条物品类别
     * @return
     */
    int addResType(ResType resType);

    int updateResType(ResType resType);

}
