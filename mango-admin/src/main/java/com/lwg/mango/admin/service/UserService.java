package com.lwg.mango.admin.service;

import com.lwg.mango.admin.pojo.SysUser;
import com.lwg.mango.core.service.CurdService;

import java.util.List;

public interface UserService extends CurdService<SysUser> {
    List<SysUser> findAll();
}
