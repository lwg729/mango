package com.lwg.mango.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lwg.mango.admin.mapper.SysDictMapper;
import com.lwg.mango.admin.pojo.SysDict;
import com.lwg.mango.admin.service.DictService;
import com.lwg.mango.core.page.MybatisPageHelper;
import com.lwg.mango.core.page.PageRequest;
import com.lwg.mango.core.page.PageResult;
import com.lwg.mango.core.service.CurdService;

@Service
public class DictServiceImpl implements DictService, CurdService<SysDict> {

    @Autowired
    private SysDictMapper dictMapper;

    @Override
    public int save(SysDict record) {
        if (record.getId()==null ||record.getId()==0){
            return dictMapper.insertSelective(record);
        }else {
            return dictMapper.updateByPrimaryKeySelective(record);
        }
    }

    @Override
    public int deleteById(SysDict record) {
        return dictMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysDict> records) {
        for (SysDict record : records) {
            deleteById(record);
        }
        return 1;
    }

    @Override
    public SysDict findById(Long id) {
        return dictMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object param = pageRequest.getParam("label");
        if (param!=null){
            return MybatisPageHelper.findPage(pageRequest,dictMapper,"findPageByLabel",param);
        }
        return MybatisPageHelper.findPage(pageRequest,dictMapper);
    }

    public List<SysDict> findByLabel(String label){
        return dictMapper.findByLabel(label);
    }

}
