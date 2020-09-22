package com.lwg.mango.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lwg.mango.admin.pojo.SysDept;
import com.lwg.mango.admin.service.impl.DeptServiceImpl;
import com.lwg.mango.core.http.HttpResult;

@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptServiceImpl deptService;

    @PostMapping(value="/save")
    public HttpResult save(@RequestBody SysDept record) {
        return HttpResult.ok(deptService.save(record));
    }

    @PostMapping(value="/delete")
    public HttpResult delete(@RequestBody List<SysDept> records) {
        return HttpResult.ok(deptService.delete(records));
    }

    @GetMapping(value="/findTree")
    public HttpResult findTree() {
        return HttpResult.ok(deptService.findTree());
    }
}
