package com.chen.service;

import com.chen.domain.ResponseResult;
import com.chen.entity.User;

/**
 * @author Amin
 * @version 1.0
 * @date 2022/9/1/001 17:33
 */
public interface LoginServcie {

    ResponseResult login(User user);

    ResponseResult logout();
}
