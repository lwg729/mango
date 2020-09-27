package com.lwg.mango.admin.service;

import com.lwg.mango.admin.pojo.SysUser;
import com.lwg.mango.core.page.PageRequest;
import com.lwg.mango.core.page.PageResult;
import com.lwg.mango.core.service.CurdService;

import java.io.File;
import java.util.List;
import java.util.Set;

public interface UserService extends CurdService<SysUser> {
    List<SysUser> findAll();

    SysUser findByName(String name);

    /**
     * 查找用户的菜单权限标识集合
     * @param userName
     * @return
     */
    Set<String> findPermissions(String userName);

    PageResult findPageUserRoles(PageRequest pageRequest);

    File createUserExcelFile(PageRequest pageRequest);
}
