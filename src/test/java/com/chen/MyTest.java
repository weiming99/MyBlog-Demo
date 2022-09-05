package com.chen;

import com.chen.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Amin
 * @version 1.0
 * @date 2022/8/30/030 16:56
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MyTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void test() {

    }


}
