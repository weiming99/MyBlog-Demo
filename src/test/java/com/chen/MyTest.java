package com.chen;

import com.alibaba.fastjson.JSONObject;
import com.chen.beans.User;
import com.chen.beans.UserExample;
import com.chen.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Amin
 * @version 1.0
 * @date 2022/8/30/030 16:56
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MyTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountEqualTo("nihao");
        List<User> users = userMapper.selectByExample(userExample);
        System.out.println("users = " + JSONObject.toJSONString(users));
    }


}
