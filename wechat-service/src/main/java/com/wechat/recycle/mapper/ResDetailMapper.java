package com.wechat.recycle.mapper;

import com.wechat.recycle.entity.ResDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResDetailMapper {

    ResDetail selectOne(Integer id);

    /**
     * 根据类别id查询
     * @param typeId 类别id
     * @return ResDetails
     */
    List<ResDetail> selectByType(Integer typeId);

    List<ResDetail> selectByAll();

    List<ResDetail> selectByName(@Param("typeId")Integer typeId, @Param("name")String name);

    /**
     * 在类别下新增一项物品
     * @return
     */
    int addResDetail(ResDetail resDetail);

    int updateResDetail(ResDetail resDetail);

    int deleteOne(Integer id);

}
