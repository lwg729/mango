package com.lwg.mango.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lwg.mango.admin.mapper.SysLoginLogMapper;
import com.lwg.mango.admin.pojo.SysLoginLog;
import com.lwg.mango.admin.service.LoginLogService;
import com.lwg.mango.core.page.MybatisPageHelper;
import com.lwg.mango.core.page.PageRequest;
import com.lwg.mango.core.page.PageResult;

@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private SysLoginLogMapper loginLogMapper;

    @Override
    public int save(SysLoginLog record) {
        if(record.getId() == null || record.getId() == 0) {
            return loginLogMapper.insertSelective(record);
        }
        return loginLogMapper.updateByPrimaryKeySelective(record);
    }


    @Override
    public int deleteById(SysLoginLog record) {
        return loginLogMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysLoginLog> records) {
        for(SysLoginLog record:records) {
            deleteById(record);
        }
        return 1;
    }

    @Override
    public SysLoginLog findById(Long id) {
        return loginLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object userName = pageRequest.getParam("userName");
        if (userName!=null){
            return MybatisPageHelper.findPage(pageRequest,loginLogMapper,"findPageByUserName",userName);
        }
        Object status = pageRequest.getParam("status");
        if (status!=null){
            return MybatisPageHelper.findPage(pageRequest,loginLogMapper,"findPageByStatus",status);
        }
        return MybatisPageHelper.findPage(pageRequest,loginLogMapper);
    }
}
