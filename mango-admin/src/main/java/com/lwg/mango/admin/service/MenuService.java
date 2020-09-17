package com.lwg.mango.admin.service;

import java.util.List;

import com.lwg.mango.admin.pojo.SysMenu;

public interface MenuService {

    List<SysMenu> findMenuWithRoles();
}
