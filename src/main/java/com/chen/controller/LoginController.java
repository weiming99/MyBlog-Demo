package com.chen.controller;

import com.chen.vo.ResultVO;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author com.chen
 * @crete 2022-08-29-20:59
 */
@RestController
public class LoginController {

    //@Autowired
    //private UserService userService;
    //
    //@Autowired
    //private UserMapper userMapper;

    @PostMapping("/login")
    public ResultVO login(@RequestBody JSONObject jsonObject){
        if (!jsonObject.containsKey("username")  || !jsonObject.containsKey("password")) {
            return new ResultVO(0,"登陆失败",null);
        }
        //List<User> userList = userService.selectByAcount(jsonObject.getString("username"));
        //System.out.println(userList);
        return new ResultVO(1,"登陆成功","123456");
    }

}
