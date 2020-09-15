package com.lwg.mango.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwg.mango.admin.pojo.SysUser;
import com.lwg.mango.admin.service.impl.UserServiceImpl;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/findAll")
    public List<SysUser> findAll(){
        return userService.findAll();
    }
}
