package com.lwg.mango.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lwg.mango.admin.mapper.SysUserMapper;
import com.lwg.mango.admin.pojo.SysUser;
import com.lwg.mango.admin.service.UserService;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper userMapper;
    @Override
    public List<SysUser> findAll() {
        return userMapper.findAll();
    }
}
