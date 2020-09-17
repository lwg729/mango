package com.lwg.mango.admin.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lwg.mango.admin.pojo.SysMenu;
import com.lwg.mango.admin.service.MenuService;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/findMenuWithRoles",method = RequestMethod.POST)
    public List<SysMenu> findMenuWithRoles(){
        return menuService.findMenuWithRoles();
    }
}
