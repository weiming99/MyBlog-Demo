package com.chen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.entity.User;
import com.chen.mapper.UserMapper;
import com.chen.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【sys_user(用户表)】的数据库操作Service实现
 * @createDate 2022-09-05 15:44:10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

}




