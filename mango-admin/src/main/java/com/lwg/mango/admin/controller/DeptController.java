package com.lwg.mango.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.lwg.mango.admin.pojo.SysDept;
import com.lwg.mango.admin.service.impl.DeptServiceImpl;
import com.lwg.mango.core.http.HttpResult;

@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptServiceImpl deptService;

    @PreAuthorize("hasAnyAuthority('sys:dept:add') AND hasAnyAuthority('sys:dept:edit')")
    @PostMapping(value="/save")
    public HttpResult save(@RequestBody SysDept record) {
        return HttpResult.ok(deptService.save(record));
    }

    @PreAuthorize("hasAnyAuthority('sys:dept:delete')")
    @PostMapping(value="/delete")
    public HttpResult delete(@RequestBody List<SysDept> records) {
        return HttpResult.ok(deptService.delete(records));
    }

    @PreAuthorize("hasAnyAuthority('sys:dept:view')")
    @GetMapping(value="/findTree")
    public HttpResult findTree() {
        return HttpResult.ok(deptService.findTree());
    }
}
