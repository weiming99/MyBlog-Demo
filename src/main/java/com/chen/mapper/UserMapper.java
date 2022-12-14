package com.chen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【sys_user(用户表)】的数据库操作Mapper
 * @createDate 2022-09-05 15:44:10
 * @Entity com.chen.domain.User
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> selectAllByStatus(@Param("status") String status);
}




