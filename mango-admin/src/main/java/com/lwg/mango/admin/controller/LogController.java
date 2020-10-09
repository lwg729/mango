package com.lwg.mango.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwg.mango.admin.pojo.SysLog;
import com.lwg.mango.admin.service.impl.LogServiceImpl;
import com.lwg.mango.core.http.HttpResult;
import com.lwg.mango.core.page.PageRequest;

@RestController
@RequestMapping("log")
public class LogController {

    @Autowired
    private LogServiceImpl logService;
    @PreAuthorize("hasAnyAuthority('sys:log:view')")
    @PostMapping(value="/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(logService.findPage(pageRequest));
    }
    @PreAuthorize("hasAnyAuthority('sys:log:delete')")
    @PostMapping(value="/delete")
    public HttpResult delete(@RequestBody List<SysLog> records) {
        return HttpResult.ok(logService.delete(records));
    }
}
