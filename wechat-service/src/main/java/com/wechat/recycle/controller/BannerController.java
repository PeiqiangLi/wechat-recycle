package com.wechat.recycle.controller;

import com.wechat.recycle.common.utils.Result;
import com.wechat.recycle.common.utils.ResultUtil;
import com.wechat.recycle.common.utils.StatusCodeEnum;
import com.wechat.recycle.entity.Banner;
import com.wechat.recycle.service.BannerService;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping(value = "/updateXuHao", method = RequestMethod.GET)
    public Result updateXuHao(Integer id) {
        if (id == null) {
            return ResultUtil.error(StatusCodeEnum.PARAMS_EXCEPTION);
        }
        int count = bannerService.updateXuHao(1,id);
        if (count <= 0) return ResultUtil.error("1002","更新失败");
        return ResultUtil.success();
    }

    @RequestMapping(value = "/updateBanner", method = RequestMethod.POST)
    public Result updateBanner(@RequestBody Banner banner) {
        if (banner.getRemarks() == ""
                || banner.getImgUrl() == "") {
            return ResultUtil.error(StatusCodeEnum.PARAMS_EXCEPTION);
        }
        int count = bannerService.updateBanner(banner);
        if (count <= 0) return ResultUtil.error("1002","更新失败");
        return ResultUtil.success();
    }

}
