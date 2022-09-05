package com.chen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【sys_menu(菜单表)】的数据库操作Mapper
 * @createDate 2022-09-05 15:44:10
 * @Entity com.chen.domain.Menu
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> selectAllByStatus(@Param("status") String status);
}




