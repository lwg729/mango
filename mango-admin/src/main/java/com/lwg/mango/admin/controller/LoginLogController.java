package com.lwg.mango.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwg.mango.admin.pojo.SysLoginLog;
import com.lwg.mango.admin.service.impl.LoginLogServiceImpl;
import com.lwg.mango.core.http.HttpResult;
import com.lwg.mango.core.page.PageRequest;

@RestController
@RequestMapping("loginLog")
public class LoginLogController {

    @Autowired
    private LoginLogServiceImpl loginLogService;

    @PreAuthorize("hasAnyAuthority('sys:loginLog:view')")
    @PostMapping(value="/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(loginLogService.findPage(pageRequest));
    }

    @PreAuthorize("hasAnyAuthority('sys:loginLog:delete')")
    @PostMapping(value="/delete")
    public HttpResult delete(@RequestBody List<SysLoginLog> records) {
        return HttpResult.ok(loginLogService.delete(records));
    }
}
