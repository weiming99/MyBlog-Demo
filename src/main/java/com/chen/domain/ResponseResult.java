package com.chen.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Amin
 * @version 1.0
 * @date 2022/9/1/001 15:53
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据，
     */
    private Object data;

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponseResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseResult ok() {
        return new ResponseResult(200, "操作成功");
    }

    public static ResponseResult error(Integer code, String str) {
        return new ResponseResult(code, str);
    }

    public static ResponseResult error(String str) {
        return new ResponseResult(-1, str);
    }
}
