package com.chen.controller;

import com.chen.domain.ResponseResult;
import com.chen.entity.User;
import com.chen.service.LoginServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginServcie loginServcie;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        return loginServcie.login(user);
    }

    @PostMapping("/user/logout")
    public ResponseResult logout() {
        return loginServcie.logout();
    }

}