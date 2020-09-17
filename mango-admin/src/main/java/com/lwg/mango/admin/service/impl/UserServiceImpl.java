package com.lwg.mango.admin.service.impl;

import com.lwg.mango.admin.mapper.SysUserMapper;
import com.lwg.mango.admin.pojo.SysUser;
import com.lwg.mango.admin.service.UserService;
import com.lwg.mango.core.page.MybatisPageHelper;
import com.lwg.mango.core.page.PageRequest;
import com.lwg.mango.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper userMapper;
    @Override
    public List<SysUser> findAll() {
        return userMapper.findAll();
    }

    @Override
    public SysUser findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public Set<String> findPermissions(String userName) {
        return null;
    }

    @Override
    public PageResult findPageUserRoles(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest,userMapper,"findPageUserRoles");
    }

    @Override
    public int save(SysUser record) {
        if (record.getId()==null||record.getId()==0){
            return userMapper.insertSelective(record);
        }else {
            return userMapper.updateByPrimaryKeySelective(record);
        }
    }

    @Override
    public int deleteById(SysUser record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(List<SysUser> records) {
        for (SysUser record : records) {
            deleteById(record);
        }
        return 1;
    }

    @Override
    public SysUser findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, userMapper);
    }
}
