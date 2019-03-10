package com.wechat.recycle.service.impl;

import com.wechat.recycle.entity.Banner;
import com.wechat.recycle.mapper.BannerMapper;
import com.wechat.recycle.service.BannerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Resource
    private BannerMapper bannerMapper;

    @Override
    public List<Banner> selectBanners() {
        return bannerMapper.selectBanners();
    }

    @Override
    public List<Banner> selectUseBanners() {
        return bannerMapper.selectUseBanners();
    }

}
