package com.lwg.mango.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lwg.mango.admin.constant.SysConstants;
import com.lwg.mango.admin.mapper.SysMenuMapper;
import com.lwg.mango.admin.pojo.SysMenu;
import com.lwg.mango.admin.service.MenuService;
import com.lwg.mango.core.page.MybatisPageHelper;
import com.lwg.mango.core.page.PageRequest;
import com.lwg.mango.core.page.PageResult;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public List<SysMenu> findMenuWithRoles() {
        return menuMapper.findMenuWithRoles();
    }

    @Override
    public List<SysMenu> findAll() {
        return menuMapper.findAll();
    }

    @Override
    public int save(SysMenu record) {
        if (record.getId() == null || record.getId() == 0) {
            return menuMapper.insertSelective(record);
        }
        return menuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteById(SysMenu record) {
        return menuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(List<SysMenu> records) {
        for (SysMenu record : records) {
            deleteById(record);
        }
        return 1;
    }

    @Override
    public SysMenu findById(Long id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, menuMapper);
    }

    @Override
    public List<SysMenu> findTree(String name, int menuType) {
        List<SysMenu> parentMenus = new ArrayList<>();
        //为什么要判断菜单名字为空的情况呢 因为当我们点击菜单的时候需要找的是已存在的某菜单的
        //children  若没有该父类菜单只能查询全部菜单 或者改菜单的name是系统管理员的name 也只能查询全部,因为它拥有所有的菜单权限
        //这也就是为什么要重新写一个方法findByName的原因
        List<SysMenu> menus = findByName(name);
        for (SysMenu menu : menus) {
            if (menu.getParentId() == 0 || menu.getParentId() == null) {
                menu.setLevel(0);
                //判断这个menu是否有children
                if (exit(parentMenus, menu)) {
                    parentMenus.add(menu);
                }
            }
        }
        parentMenus.sort(((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum())));
        findChildren(parentMenus, menus, menuType);
        return parentMenus;
    }

    private void findChildren(List<SysMenu> parentMenus, List<SysMenu> menus, int menuType) {
        for (SysMenu parentMenu : parentMenus) {
            ArrayList<SysMenu> childrens = new ArrayList<>();
            for (SysMenu menu : menus) {
                if (menuType==1&&menuType==2){
                    continue;
                }
                if (parentMenu.getId()!=null && parentMenu.getId().equals(menu.getParentId())){
                    menu.setParentName(parentMenu.getName());
                    menu.setLevel(parentMenu.getLevel()+1);
                    if (exit(childrens,menu)){
                        childrens.add(menu);
                    }
                }
            }
            parentMenu.setChildren(childrens);
            childrens.sort(((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum())));
            findChildren(childrens,menus,menuType);
        }
    }

    @Override
    public List<SysMenu> findByName(String name) {
        if (name == null || "".equals(name) || SysConstants.ADMIN.equalsIgnoreCase(name)) {
            return menuMapper.findAll();
        }
        return menuMapper.findByUserName(name);
    }

    public boolean exit(List<SysMenu> parentMenus, SysMenu menu) {
        boolean exit = true;
        for (SysMenu parentMenu : parentMenus) {
            if (menu.getId().equals(parentMenu.getId())) {
                exit = false;
            }
        }
        return exit;
    }
}
