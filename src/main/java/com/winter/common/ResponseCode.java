package com.winter.common;

/**
 * 响应码
 * 0 -- 成功
 * 1 -- 失败
 * 10 -- 需要登录
 * 2 -- 参数不合法
 * 100 -- 返回新token
 */
public enum ResponseCode {
    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT"),
    FRESH_TOKEN(100,"FRESH_TOKEN");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
