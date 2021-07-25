package com.whitestorm.common;


import lombok.Data;

import java.io.Serializable;

@Data

public class CommonResult implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * 返回代码
     */
    private Integer code;

    /**
     * 失败消息
     */
    private String msg;

    /**
     * 时间戳
     */


    /**
     * 返回数据
     */
    private Object data;

    public CommonResult(String msg, int code)
    {
        this.setMsg(msg);
        this.setCode(code);

    }

    public CommonResult(String msg, int code, Object data)
    {
        this.setMsg(msg);
        this.setCode(code);
        this.setData(data);

    }

    public CommonResult(Integer code, String msg, Object data)
    {
        this.code = code;
        this.msg = msg;
        this.data = data;

    }

    public static CommonResult success()
    {
        return new CommonResult(CommonResultCode.SUCCESS.getMsg(), CommonResultCode.SUCCESS.getCode());
    }

    public static CommonResult success(Object data)
    {
        return new CommonResult(CommonResultCode.SUCCESS.getMsg(), CommonResultCode.SUCCESS.getCode(), data);
    }

    public static CommonResult success(String msg, Object data)
    {
        return new CommonResult(msg, HttpStatus.SUCCESS, data);
    }

    public static CommonResult success(CommonResultCode CommonResultCode, Object data)
    {
        return new CommonResult(CommonResultCode.getMsg(), CommonResultCode.getCode(), data);
    }

    public static CommonResult success(CommonResultCode CommonResultCode)
    {
        return new CommonResult(CommonResultCode.getMsg(), CommonResultCode.getCode());
    }

    public static CommonResult fail()
    {
        return new CommonResult(CommonResultCode.SERVER_ERROR.getMsg(), CommonResultCode.SERVER_ERROR.getCode());
    }

    public static CommonResult fail(String msg)
    {
        return new CommonResult(msg, HttpStatus.ERROR);
    }

    public static CommonResult fail(CommonResultCode CommonResultCode)
    {
        return new CommonResult(CommonResultCode.getMsg(), CommonResultCode.getCode());
    }

    public static CommonResult fail(CommonResultCode CommonResultCode, Object data)
    {
        return new CommonResult(CommonResultCode.getMsg(), CommonResultCode.getCode(), data);
    }

    @Override
    public String toString()
    {
        return "{" + "\"code\":" + code + ", \"msg\":\"" + msg + '\"' + ", \"data\":\"" + data + '\"' + ", \"time\":\""  + '}';
    }
}
