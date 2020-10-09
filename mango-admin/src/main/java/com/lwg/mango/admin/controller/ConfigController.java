package com.lwg.mango.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lwg.mango.admin.pojo.SysConfig;
import com.lwg.mango.admin.pojo.SysMenu;
import com.lwg.mango.admin.service.impl.ConfigServiceImpl;
import com.lwg.mango.core.http.HttpResult;
import com.lwg.mango.core.page.PageRequest;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private ConfigServiceImpl configService;
    @PreAuthorize("hasAnyAuthority('sys:config:add') AND hasAnyAuthority('sys:config:edit')")
    @PostMapping("/save")
    public HttpResult save(@RequestBody SysConfig record){
        return HttpResult.ok(configService.save(record));
    }

    @PreAuthorize("hasAnyAuthority('sys:config:delete')")
    @PostMapping("delete")
    public HttpResult delete(List<SysConfig> records){
        return HttpResult.ok(configService.delete(records));
    }

    @PreAuthorize("hasAnyAuthority('sys:config:view')")
    @GetMapping("findById")
    public HttpResult findById(@RequestParam Long id){
        return HttpResult.ok(configService.findById(id));
    }

    @PreAuthorize("hasAnyAuthority('sys:config:view')")
    @PostMapping("findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest){
        return HttpResult.ok(configService.findPage(pageRequest));
    }

    @PreAuthorize("hasAnyAuthority('sys:config:view')")
    @GetMapping("/findByLabel")
    public HttpResult findByLabel(@RequestParam String label){
        return HttpResult.ok(configService.findByLabel(label));
    }
}
