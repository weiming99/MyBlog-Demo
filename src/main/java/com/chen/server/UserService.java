package com.chen.server;

import com.chen.beans.User;
import com.chen.beans.UserExample;
import com.chen.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Amin
 * @version 1.0
 * @date 2022/8/30/030 16:52
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> selectByAcount(String account) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountEqualTo(account);
        return userMapper.selectByExample(userExample);
    }
}
