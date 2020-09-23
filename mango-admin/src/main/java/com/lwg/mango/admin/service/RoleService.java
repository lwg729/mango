package com.lwg.mango.admin.service;

import java.util.List;

import com.lwg.mango.admin.pojo.SysMenu;
import com.lwg.mango.admin.pojo.SysRole;
import com.lwg.mango.admin.pojo.SysRoleMenu;
import com.lwg.mango.core.service.CurdService;

public interface RoleService extends CurdService<SysRole> {

    /**
     * 查询全部
     */
    List<SysRole> findAll();

    /**
     * 根据名称查询
     */
    List<SysRole> findByName(String name);

    /**
     * 查询角色的菜单集合
     */

    List<SysMenu> findRoleMenus(Long id);

    /**
     * 保存角色菜单
     */
    int saveRoleMenus(List<SysRoleMenu> records);
}
