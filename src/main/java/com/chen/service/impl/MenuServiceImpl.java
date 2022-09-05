package com.chen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.entity.Menu;
import com.chen.mapper.MenuMapper;
import com.chen.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【sys_menu(菜单表)】的数据库操作Service实现
 * @createDate 2022-09-05 15:44:10
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
        implements MenuService {

}




