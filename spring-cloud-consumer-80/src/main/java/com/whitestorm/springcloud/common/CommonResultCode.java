package com.whitestorm.springcloud.common;

/**
 * @author admin
 */

public enum CommonResultCode {
    SUCCESS(HttpStatus.SUCCESS, "SUCCESS"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "参数或者语法不对"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "认证失败"),
    LOGIN_ERROR(HttpStatus.UNAUTHORIZED, "登陆失败,用户名或密码无效"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "权限不住,禁止访问"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "请求的资源不存在"),
    OPERATE_ERROR(HttpStatus.BAD_METHOD, "操作失败,请求操作的资源不存在"),
    TIME_OUT(HttpStatus.TIME_OUT, "请求超时"),
    SERVER_ERROR(HttpStatus.ERROR, "服务器内部错误"),
    REGISTER_SUCCESS(HttpStatus.SUCCESS, "帐号注册成功"),
    REGISTER_FAIL(HttpStatus.ERROR, "帐号注册失败");

    private final int code;

    private final String msg;

    CommonResultCode(int code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public int getCode()
    {
        return code;
    }

    public String getMsg()
    {
        return msg;
    }
}
