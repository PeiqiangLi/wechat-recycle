package com.wechat.recycle.common.utils;

import java.io.Serializable;

/**
 * @Author: PeiqiangLi
 * @Description:
 * @Date: Created in 10:38 2019/2/21
 * @Modify By:
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private String code;
    private String msg;
    private T data;

    //保留默认空构造器
    public Result() {
        super();
    }

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(StatusCodeEnum code, T data) {
        this(code.getCode(), code.getMsg(), data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

}
