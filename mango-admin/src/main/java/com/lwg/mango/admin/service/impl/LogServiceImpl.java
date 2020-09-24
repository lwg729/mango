package com.lwg.mango.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lwg.mango.admin.mapper.SysLogMapper;
import com.lwg.mango.admin.pojo.SysLog;
import com.lwg.mango.admin.service.LogService;
import com.lwg.mango.core.page.MybatisPageHelper;
import com.lwg.mango.core.page.PageRequest;
import com.lwg.mango.core.page.PageResult;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private SysLogMapper logMapper;

    @Override
    public int save(SysLog record) {
        if(record.getId() == null || record.getId() == 0) {
            return logMapper.insertSelective(record);
        }
        return logMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteById(SysLog record) {
        return logMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysLog> records) {
        for(SysLog record:records) {
            deleteById(record);
        }
        return 1;
    }

    @Override
    public SysLog findById(Long id) {
        return logMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object label = pageRequest.getParam("userName");
        if(label != null) {
            return MybatisPageHelper.findPage(pageRequest, logMapper, "findPageByUserName", label);
        }
        return MybatisPageHelper.findPage(pageRequest, logMapper);
    }

}
