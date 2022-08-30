package com.chen.util;

import com.alibaba.fastjson.JSONObject;
import com.chen.vo.ResultVO;

import java.time.OffsetDateTime;

/**
 * @author Amin
 * @version 1.0
 * @date 2022/8/30/030 16:55
 */
public class CheckRequestBodyUtil {


    public static ResultVO checkParams(JSONObject jsonObject, String... params) {
        if (params != null && params.length > 0) {
            for (String key : params) {
                String value = jsonObject.getString(key);
                if (value == null) {
                    return ResultVO.error("缺少" + key + "参数");
                } else if ("".equals(value.trim())) {
                    return ResultVO.error(key + "参数为空值");
                }
            }
        } else {
            for (String key : jsonObject.keySet()) {
                String value = jsonObject.getString(key);
                if (value == null) {
                    return ResultVO.error("缺少" + key + "参数");
                } else if ("".equals(value.trim())) {
                    return ResultVO.error(key + "参数为空值");
                }
            }
        }

        return ResultVO.ok();
    }

}
