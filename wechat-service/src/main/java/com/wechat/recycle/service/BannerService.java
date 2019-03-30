package com.wechat.recycle.service;

import com.wechat.recycle.entity.Banner;

import java.util.List;

public interface BannerService {

    List<Banner> selectBanners();

    List<Banner> selectUseBanners();

    int updateXuHao(Integer xuhao, Integer id);

    int updateBanner(Banner banner);

}
