package com.lwg.mango.admin.controller;

import com.github.pagehelper.Page;
import com.lwg.mango.admin.pojo.SysUser;
import com.lwg.mango.admin.service.impl.UserServiceImpl;
import com.lwg.mango.common.utils.FileUtils;
import com.lwg.mango.core.http.HttpResult;
import com.lwg.mango.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public List<SysUser> findAll(){
        return userService.findAll();
    }

    @RequestMapping(value = "/findPage",method = RequestMethod.POST)
    public HttpResult findPage(@RequestBody PageRequest pageRequest){

        return HttpResult.ok(userService.findPage(pageRequest));
    }
    @PostMapping("/save")
    public HttpResult save(@RequestBody SysUser record){
        return HttpResult.ok(userService.save(record));
    }

    @PostMapping("/delete")
    public HttpResult delete(List<SysUser> records){
        return HttpResult.ok(userService.delete(records));
    }

    @GetMapping("/findByName")
    public HttpResult findByName(@RequestParam String name){
        return HttpResult.ok(userService.findByName(name));
    }

    @PostMapping("/findPageUserRoles")
    public HttpResult findPageUserRoles(@RequestBody PageRequest pageRequest){
        return HttpResult.ok(userService.findPageUserRoles(pageRequest));
    }

    @GetMapping(value="/findPermissions")
    public HttpResult findPermissions(@RequestParam String name) {
        return HttpResult.ok(userService.findPermissions(name));
    }

    //导出
    @PostMapping(value="/exportExcelUser")
    public void exportExcelUser(@RequestBody PageRequest pageRequest, HttpServletResponse res){
        File file = userService.createUserExcelFile(pageRequest);
        FileUtils.downloadFile(res,file,file.getName());
    }
}
