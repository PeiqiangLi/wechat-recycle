package com.wechat.recycle.common.utils;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 10:42 2019/2/21
 * @Modify By:
 */
public final class ResultUtil {

    private ResultUtil() {

    }

    /**成功且带数据**/
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(StatusCodeEnum.SUCCESS.getCode());
        result.setMsg(StatusCodeEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }
    /**成功但不带数据**/
    public static Result success(){
        Result result = new Result();
        result.setCode(StatusCodeEnum.SUCCESS.getCode());
        result.setMsg(StatusCodeEnum.SUCCESS.getMsg());
        return result;
    }
    /**成功返回分页对象**/
    public static Result pageResult(PageInfo page) {
        Map data = new HashMap();
        data.put("list", page.getList());
        data.put("pageNum", page.getPageNum());
        data.put("pageSize", page.getPageSize());
        data.put("total", page.getTotal());
        data.put("totalPages", page.getPages());
        return new Result(StatusCodeEnum.SUCCESS, data);
    }

    /**失败**/
    public static Result error(String code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**失败返回定义的msg**/
    public static Result error(StatusCodeEnum statusCodeEnum){
        Result result = new Result();
        result.setCode(statusCodeEnum.getCode());
        result.setMsg(statusCodeEnum.getMsg());
        return result;
    }

}
