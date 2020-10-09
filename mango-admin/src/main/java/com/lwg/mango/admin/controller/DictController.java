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

import com.lwg.mango.admin.pojo.SysDict;
import com.lwg.mango.admin.service.impl.DictServiceImpl;
import com.lwg.mango.core.http.HttpResult;
import com.lwg.mango.core.page.PageRequest;

@RestController
@RequestMapping("dict")
public class DictController {

    @Autowired
    private DictServiceImpl dictService;

    @PreAuthorize("hasAnyAuthority('sys:dict:add') AND hasAnyAuthority('sys:dict:edit')")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public HttpResult save(@RequestBody SysDict record) {
        return HttpResult.ok(dictService.save(record));
    }

    @PreAuthorize("hasAnyAuthority('sys:dict:delete')")
    @PostMapping("/delete")
    public HttpResult delete(@RequestBody List<SysDict> records) {
        return HttpResult.ok(dictService.delete(records));
    }

    @PreAuthorize("hasAnyAuthority('sys:dict:view')")
    @PostMapping("/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(dictService.findPage(pageRequest));
    }
    @PreAuthorize("hasAnyAuthority('sys:dict:view')")
    @GetMapping("/findByLabel")
    public HttpResult findByLabel(@RequestParam String label) {
        return HttpResult.ok(dictService.findByLabel(label));
    }
}
