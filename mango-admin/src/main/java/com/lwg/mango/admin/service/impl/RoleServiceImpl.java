package com.lwg.mango.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lwg.mango.admin.constant.SysConstants;
import com.lwg.mango.admin.mapper.SysMenuMapper;
import com.lwg.mango.admin.mapper.SysRoleMapper;
import com.lwg.mango.admin.mapper.SysRoleMenuMapper;
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

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

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

    //此id为roleId
    @Override
    public List<SysMenu> findRoleMenus(Long id) {
        SysRole role = roleMapper.selectByPrimaryKey(id);
        if (SysConstants.ADMIN.equalsIgnoreCase(role.getName())){
            //该角色为管理员 拥有所有权限url
           return menuMapper.findAll();
        }
        return menuMapper.findRoleMenus(id);
    }

    @Override
    public int saveRoleMenus(List<SysRoleMenu> records) {
        if(records==null ||records.isEmpty()){
            return 1;
        }
        Long roleId = records.get(0).getRoleId();
        roleMenuMapper.deleteByRoleId(roleId);
        for (SysRoleMenu record : records) {
            roleMenuMapper.insertSelective(record);
        }
        return 1;
    }
}
