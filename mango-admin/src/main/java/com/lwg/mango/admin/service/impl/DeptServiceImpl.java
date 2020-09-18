package com.lwg.mango.admin.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lwg.mango.admin.pojo.SysDept;
import com.lwg.mango.admin.service.DeptService;
import com.lwg.mango.core.page.PageRequest;
import com.lwg.mango.core.page.PageResult;

@Service
public class DeptServiceImpl implements DeptService {
    @Override
    public List<SysDept> findTree() {
        return null;
    }

    @Override
    public int save(SysDept record) {
        return 0;
    }

    @Override
    public int deleteById(SysDept record) {
        return 0;
    }

    @Override
    public int delete(List<SysDept> records) {
        return 0;
    }

    @Override
    public SysDept findById(Long id) {
        return null;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return null;
    }
}
