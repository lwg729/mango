package com.lwg.mango.admin.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lwg.mango.admin.pojo.SysMenu;
import com.lwg.mango.admin.service.MenuService;
import com.lwg.mango.core.http.HttpResult;
import com.lwg.mango.core.page.PageRequest;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PreAuthorize("hasAnyAuthority('sys:menu:view')")
    @RequestMapping(value = "/findMenuWithRoles", method = RequestMethod.POST)
    public List<SysMenu> findMenuWithRoles() {
        return menuService.findMenuWithRoles();
    }

    @PreAuthorize("hasAnyAuthority('sys:menu:add') AND hasAnyAuthority('sys:menu:edit')")
    @PostMapping("/save")
    public HttpResult save(@RequestBody SysMenu record) {
        return HttpResult.ok(menuService.save(record));
    }

    @PreAuthorize("hasAnyAuthority('sys:menu:delete')")
    @PostMapping("delete")
    public HttpResult delete(List<SysMenu> records) {
        return HttpResult.ok(menuService.delete(records));
    }

    @PreAuthorize("hasAnyAuthority('sys:menu:view')")
    @GetMapping("findById")
    public HttpResult findById(@RequestParam Long id) {
        return HttpResult.ok(menuService.findById(id));
    }

    @PreAuthorize("hasAnyAuthority('sys:menu:view')")
    @PostMapping("findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(menuService.findPage(pageRequest));
    }
    @PreAuthorize("hasAnyAuthority('sys:menu:view')")
    @PostMapping("findAll")
    public HttpResult findAll() {
        return HttpResult.ok(menuService.findAll());
    }

    @PreAuthorize("hasAnyAuthority('sys:menu:view')")
    //查找导航菜单树
    @GetMapping("/findNavTree")
    public HttpResult findNavTree(@RequestParam String name) {
        return HttpResult.ok(menuService.findTree(name, 1));
    }

    @PreAuthorize("hasAnyAuthority('sys:menu:view')")
    @GetMapping(value = "/findMenuTree")
    public HttpResult findMenuTree() {
        return HttpResult.ok(menuService.findTree(null, 0));
    }
}
