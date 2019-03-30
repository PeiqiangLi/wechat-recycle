package com.wechat.recycle.mapper;

import com.wechat.recycle.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerMapper {

    List<Banner> selectBanners();

    List<Banner> selectUseBanners();

    int updateXuHao(@Param("xuhao")Integer xuhao, @Param("id")Integer id);

    int updateBanner(Banner banner);

}
