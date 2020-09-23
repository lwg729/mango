package com.lwg.mango.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lwg.mango.admin.constant.SysConstants;
import com.lwg.mango.admin.mapper.SysRoleMapper;
import com.lwg.mango.admin.pojo.SysRole;
import com.lwg.mango.admin.pojo.SysRoleMenu;
import com.lwg.mango.admin.service.impl.RoleServiceImpl;
import com.lwg.mango.core.http.HttpResult;
import com.lwg.mango.core.page.PageRequest;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private SysRoleMapper roleMapper;

    @PostMapping("/save")
    public HttpResult save(@RequestBody SysRole record){
        return HttpResult.ok(roleService.save(record));
    }

    @PostMapping("/delete")
    public HttpResult delete(List<SysRole> records){
        return HttpResult.ok(roleService.delete(records));
    }

    @GetMapping("/findById")
    public HttpResult findByName(@RequestParam Long id){
        return HttpResult.ok(roleService.findById(id));
    }

    @PostMapping("/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest){
        return HttpResult.ok(roleService.findPage(pageRequest));
    }
    @GetMapping(value="/findRoleMenus")
    public HttpResult findRoleMenus(@RequestParam Long id) {
        return HttpResult.ok(roleService.findRoleMenus(id));
    }

    @PostMapping("/saveRoleMenus")
    public HttpResult saveRoleMenus(@RequestBody List<SysRoleMenu> records){
        for (SysRoleMenu record : records) {
            SysRole role = roleMapper.selectByPrimaryKey(record.getRoleId());
            if (SysConstants.ADMIN.equalsIgnoreCase(role.getName())){
                return HttpResult.error("超级管理员拥有所有权限,不允许修改");
            }
        }
        return HttpResult.ok(roleService.saveRoleMenus(records));
    }

}
