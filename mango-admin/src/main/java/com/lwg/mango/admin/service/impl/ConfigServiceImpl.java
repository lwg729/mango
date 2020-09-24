package com.lwg.mango.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lwg.mango.admin.mapper.SysConfigMapper;
import com.lwg.mango.admin.pojo.SysConfig;
import com.lwg.mango.admin.service.ConfigService;
import com.lwg.mango.core.page.MybatisPageHelper;
import com.lwg.mango.core.page.PageRequest;
import com.lwg.mango.core.page.PageResult;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private SysConfigMapper configMapper;

    @Override
    public int save(SysConfig record) {
        if (record.getId()==null||record.getId()==0){
            return configMapper.insertSelective(record);
        }
        return configMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteById(SysConfig record) {
        return configMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysConfig> records) {
        for (SysConfig record : records) {
            deleteById(record);
        }
        return 1;
    }

    @Override
    public SysConfig findById(Long id) {
        return configMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object label = pageRequest.getParam("label");
        if (label!=null){
            return MybatisPageHelper.findPage(pageRequest,configMapper,"findPageByLabel",label);
        }
        return MybatisPageHelper.findPage(pageRequest,configMapper);
    }

    @Override
    public List<SysConfig> findByLabel(String label) {
        return configMapper.findByLabel(label);
    }
}
