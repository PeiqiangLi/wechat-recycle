package com.wechat.recycle.controller;

import com.github.pagehelper.PageInfo;
import com.wechat.recycle.common.utils.Result;
import com.wechat.recycle.common.utils.ResultUtil;
import com.wechat.recycle.entity.ResDetail;
import com.wechat.recycle.service.ResDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/resDetail")
public class ResDetailController {

    @Resource
    private ResDetailService resDetailService;

    @RequestMapping(value = "/getResDetail", method = RequestMethod.GET)
    public Result getResDetail(Integer id) {
        ResDetail resDetail = resDetailService.selectOne(id);
        return ResultUtil.success(resDetail);
    }

    @RequestMapping(value = "/getWasteByType", method = RequestMethod.GET)
    public Result getWasteByType(Integer typeId) {
        if (typeId == 0) {
            List<ResDetail> resDetails = resDetailService.selectByAll();
            return ResultUtil.success(resDetails);
        }
        List<ResDetail> resDetails = resDetailService.selectByType(typeId);
        return ResultUtil.success(resDetails);
    }

    @RequestMapping(value = "/getWasteByName", method = RequestMethod.GET)
    public Result getWasteByName(Integer typeId, String name) {
        List<ResDetail> resDetails = resDetailService.selectByName(typeId, name);
        return ResultUtil.success(resDetails);
    }

    @RequestMapping(value = "/getWastePage", method = RequestMethod.GET)
    public Result getWastePage(Integer pageNum, Integer pageSize, Integer typeId, String name) {
        PageInfo<ResDetail> resDetails = resDetailService.selectWastePage(pageNum, pageSize, typeId, name);
        return ResultUtil.pageResult(resDetails);
    }

}
