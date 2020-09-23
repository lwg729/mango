package com.lwg.mango.admin.service;

import java.util.List;

import com.lwg.mango.admin.pojo.SysMenu;
import com.lwg.mango.core.service.CurdService;

public interface MenuService extends CurdService<SysMenu> {

    List<SysMenu> findMenuWithRoles();

    List<SysMenu> findAll();

    /**
     * 查询菜单树,用户id和用户名为空则查询全部
     * @param menuType 获取菜单类型 0：获取所有菜单，包含按钮    1: 获取所有菜单，不包含按钮
     * @return userId
     */
    List<SysMenu> findTree(String name,int menuType);

    /**
     * 根据用户名查找菜单类别
     * @param name
     * @return
     */
    List<SysMenu> findByName(String name);
}
