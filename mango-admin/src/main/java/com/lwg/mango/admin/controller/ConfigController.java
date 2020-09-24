package com.lwg.mango.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/save")
    public HttpResult save(@RequestBody SysConfig record){
        return HttpResult.ok(configService.save(record));
    }

    @PostMapping("delete")
    public HttpResult delete(List<SysConfig> records){
        return HttpResult.ok(configService.delete(records));
    }

    @GetMapping("findById")
    public HttpResult findById(@RequestParam Long id){
        return HttpResult.ok(configService.findById(id));
    }

    @PostMapping("findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest){
        return HttpResult.ok(configService.findPage(pageRequest));
    }

    @GetMapping("/findByLabel")
    public HttpResult findByLabel(@RequestParam String label){
        return HttpResult.ok(configService.findByLabel(label));
    }
}
