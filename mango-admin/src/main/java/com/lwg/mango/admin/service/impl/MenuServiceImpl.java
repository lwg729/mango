package com.lwg.mango.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lwg.mango.admin.mapper.SysMenuMapper;
import com.lwg.mango.admin.pojo.SysMenu;
import com.lwg.mango.admin.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public List<SysMenu> findMenuWithRoles() {
        return menuMapper.findMenuWithRoles();
    }
}
