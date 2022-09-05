package com.chen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【sys_role(角色表)】的数据库操作Mapper
 * @createDate 2022-09-05 14:40:44
 * @Entity com.chen.domain.Role
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select role_id from sys_user_role where user_id = #{id}")
    public List<Long> selectIdbyUserId(@Param("id") String id);

}




