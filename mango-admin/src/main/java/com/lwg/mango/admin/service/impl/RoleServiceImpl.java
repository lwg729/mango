package com.lwg.mango.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lwg.mango.admin.constant.SysConstants;
import com.lwg.mango.admin.mapper.SysMenuMapper;
import com.lwg.mango.admin.mapper.SysRoleMapper;
import com.lwg.mango.admin.pojo.SysMenu;
import com.lwg.mango.admin.pojo.SysRole;
import com.lwg.mango.admin.pojo.SysRoleMenu;
import com.lwg.mango.admin.service.RoleService;
import com.lwg.mango.core.page.MybatisPageHelper;
import com.lwg.mango.core.page.PageRequest;
import com.lwg.mango.core.page.PageResult;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public int save(SysRole record) {
        if (record.getId()==null||record.getId()==0){
           return roleMapper.insertSelective(record);
        }
        return roleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteById(SysRole record) {
        return roleMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysRole> records) {
        for (SysRole record : records) {
            deleteById(record);
        }
        return 1;
    }

    @Override
    public SysRole findById(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest,roleMapper);
    }

    @Override
    public List<SysRole> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public List<SysRole> findByName(String name) {
        return roleMapper.findByName(name);
    }

    @Override
    public List<SysMenu> findRoleMenus(Long roleId) {
        SysRole role = roleMapper.selectByPrimaryKey(roleId);
        if (SysConstants.ADMIN.equalsIgnoreCase(role.getName())){
            //该角色为管理员 拥有所有权限url
           return menuMapper.findAll();
        }
        return menuMapper.findRoleMenus(roleId);
    }

    @Override
    public int saveRoleMenus(List<SysRoleMenu> records) {

        return 0;
    }
}
