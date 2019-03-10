package com.wechat.recycle.mapper;

import com.wechat.recycle.entity.Banner;

import java.util.List;

public interface BannerMapper {

    List<Banner> selectBanners();

    List<Banner> selectUseBanners();

}
