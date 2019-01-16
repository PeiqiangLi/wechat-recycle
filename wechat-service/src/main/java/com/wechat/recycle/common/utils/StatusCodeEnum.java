package com.wechat.recycle.common.utils;

public enum StatusCodeEnum {

    // 访问成功
    SUCCESS("0000","调用成功"),

    // 通用CODE 1000 - 1999
    SYSTEM_EXCEPTION("1001","系统异常"),
    FAILED("1002","调用失败"),
    PARAMS_EXCEPTION("1003","参数异常"),
    API_NO_EXIST("1004","接口不存在"),
    USER_UNLOGIN("1005","登录失效"),
    DATA_NO_EXIST("1006","数据不存在"),
    STATE_EXCEPTION("1007","状态异常"),
    PERMISSION_DEFINED("1008","权限不足");

    // 其他CODE 2000 - 2999

    private String code;
    private String message;
    StatusCodeEnum(String code,String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
