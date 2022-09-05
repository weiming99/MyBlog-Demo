package com.chen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.entity.Role;
import com.chen.mapper.RoleMapper;
import com.chen.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【sys_role(角色表)】的数据库操作Service实现
 * @createDate 2022-09-05 14:40:44
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

}




