package com.wechat.recycle.controller;

import com.wechat.recycle.common.utils.Result;
import com.wechat.recycle.common.utils.ResultUtil;
import com.wechat.recycle.entity.ResType;
import com.wechat.recycle.service.ResTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 14:17 2019/3/5
 * @Modify By:
 */
@RestController
@RequestMapping("/resType")
public class ResTypeController {

    @Resource
    private ResTypeService resTypeService;

    @RequestMapping(value = "/getResDetail", method = RequestMethod.GET)
    public Result getAllTypes() {
        List<ResType> resTypes = resTypeService.selectAllTypes();
        return ResultUtil.success(resTypes);
    }

}
