package com.chen.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author com.chen
 * @crete 2022-08-29-21:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO {

    //响应给前端的状态码
    private int code;

    //响应给前端的提示信息
    private String msg;

    //响应给前端的数据
    private Object data;

    public static ResultVO ok() {
        return new ResultVO(1, "请求成功", null);
    }

    public static ResultVO ok(Object data) {
        return new ResultVO(1, "请求成功", data);
    }

    public static ResultVO error() {
        return new ResultVO(-1, "请求失败", null);
    }

    public static ResultVO error(String msg) {
        return new ResultVO(-1, msg, null);
    }

    public static ResultVO error(Object data) {
        return new ResultVO(-1, "请求失败", data);
    }
}