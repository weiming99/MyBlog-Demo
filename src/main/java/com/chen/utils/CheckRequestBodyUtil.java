package com.chen.utils;

import com.alibaba.fastjson.JSONObject;
import com.chen.domain.ResponseResult;

/**
 * @author Amin
 * @version 1.0
 * @date 2022/8/30/030 16:55
 */
public class CheckRequestBodyUtil {


    public static ResponseResult checkParams(JSONObject jsonObject, String[] params) {

        if (params == null) {
            return ResponseResult.ok();
        }

        for (String key : params) {
            String value = jsonObject.getString(key);
            if (value == null) {
                return ResponseResult.error("缺少" + key + "参数");
            } else if ("".equals(value.trim())) {
                return ResponseResult.error(key + "参数为空值");
            }
        }

        return new ResponseResult(200, "操作成功");
    }

}
