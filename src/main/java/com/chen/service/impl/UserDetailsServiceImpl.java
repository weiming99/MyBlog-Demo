package com.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chen.domain.LoginUser;
import com.chen.entity.Role;
import com.chen.entity.User;
import com.chen.mapper.RoleMapper;
import com.chen.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(wrapper);
        //如果查询不到数据就通过抛出异常来给出提示
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或密码错误");
        }
        // todo 变成缓存 不需要一直都去访问MySQL
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.eq("status", "0");
        List<Role> roles = roleMapper.selectList(roleQueryWrapper);

        List<Long> longs = roleMapper.selectIdbyUserId(user.getId().toString());
        List<String> roleString = roles.stream().filter(u -> longs.contains(u.getId())).map(Role::getRoleKey).collect(Collectors.toList());
        //封装成UserDetails对象返回
        return new LoginUser(user, roleString);
    }
}