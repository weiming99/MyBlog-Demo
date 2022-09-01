package com.chen.controller;

import com.alibaba.fastjson.JSONObject;
import com.chen.beans.User;
import com.chen.mapper.UserMapper;
import com.chen.server.UserService;
import com.chen.util.CheckRequestBodyUtil;
import com.chen.util.JWTUtils;
import com.chen.util.MD5Util;
import com.chen.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author com.chen
 * @crete 2022-08-29-20:59
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public ResultVO login(@RequestBody JSONObject jsonObject) {
        ResultVO resultVO = CheckRequestBodyUtil.checkParams(jsonObject, new String[]{"account", "password"});
        if (resultVO.getCode() != 1) {
            return resultVO;
        }
        List<User> userList = userService.selectByAcount(jsonObject.getString("account"));
        if (userList.isEmpty()) {
            return new ResultVO(-1, "不存该用户", null);
        } else {
            User user = userList.get(0);
            if (user.getPassword().equals(MD5Util.encode(jsonObject.getString("password")))) {
                return new ResultVO(1, "登陆成功", JWTUtils.getTokenByUser(user));
            } else {
                return new ResultVO(-1, "密码错误", null);
            }

        }
    }

}
