package com.wechat.recycle.controller;

import com.github.pagehelper.PageInfo;
import com.wechat.recycle.common.utils.Result;
import com.wechat.recycle.common.utils.ResultUtil;
import com.wechat.recycle.common.utils.StatusCodeEnum;
import com.wechat.recycle.entity.ResDetail;
import com.wechat.recycle.service.ResDetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/addResDetail", method = RequestMethod.POST)
    public Result addResDetail(@RequestBody ResDetail resDetail) {
        if (resDetail.getTypeId() == null
                || StringUtils.isEmpty(resDetail.getName())
                || StringUtils.isEmpty(resDetail.getIconUrl())
                || StringUtils.isEmpty(resDetail.getUnit())
                || resDetail.getPrice() == null) {
            return ResultUtil.error(StatusCodeEnum.PARAMS_EXCEPTION);
        }
        int count = resDetailService.addResDetail(resDetail);
        if (count <= 0) return ResultUtil.error("1002","删除失败");
        return ResultUtil.success();
    }

    @RequestMapping(value = "/updateWaste", method = RequestMethod.POST)
    public Result updateWaste(@RequestBody ResDetail resDetail) {
        if (resDetail.getName() == ""
                || resDetail.getIconUrl() == ""
                || resDetail.getUnit() == "") {
            return ResultUtil.error(StatusCodeEnum.PARAMS_EXCEPTION);
        }
        int count = resDetailService.updateResDetail(resDetail);
        if (count <= 0) return ResultUtil.error("1002","更新失败");
        return ResultUtil.success();
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

    @RequestMapping(value = "/deleteWaste", method = RequestMethod.GET)
    public Result deleteWaste(Integer id) {
        if (id == null) {
            return ResultUtil.error(StatusCodeEnum.PARAMS_EXCEPTION);
        }
        int count = resDetailService.deleteOne(id);
        if (count <= 0) return ResultUtil.error("1002","删除消息失败");
        return ResultUtil.success();
    }

}
