package com.wechat.recycle.controller;

import com.wechat.recycle.common.utils.Result;
import com.wechat.recycle.common.utils.ResultUtil;
import com.wechat.recycle.entity.Banner;
import com.wechat.recycle.service.BannerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/banner")
public class BannerController {

    @Resource
    private BannerService bannerService;

    @RequestMapping(value = "/getUsedBanners", method = RequestMethod.GET)
    public Result getUsedBanners() {
        List<Banner> banners = bannerService.selectUseBanners();
        return ResultUtil.success(banners);
    }

}
