package com.lwg.mango.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lwg.mango.admin.mapper.SysDeptMapper;
import com.lwg.mango.admin.pojo.SysDept;
import com.lwg.mango.admin.service.DeptService;
import com.lwg.mango.core.page.MybatisPageHelper;
import com.lwg.mango.core.page.PageRequest;
import com.lwg.mango.core.page.PageResult;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private SysDeptMapper deptMapper;

    @Override
    public List<SysDept> findTree() {
        ArrayList<SysDept> parentDepts = new ArrayList<>();
        List<SysDept> allDepts = deptMapper.findAll();
        for (SysDept dept : allDepts) {
            if (dept.getParentId() == null || dept.getParentId() == 0) {
                dept.setLevel(0);
                parentDepts.add(dept);
            }
        }
        findChildren(parentDepts, allDepts);
        return parentDepts;
    }

    public void findChildren(List<SysDept> parentDepts, List<SysDept> allDepts) {
        for (SysDept parentDept : parentDepts) {
            ArrayList<SysDept> children = new ArrayList<>();
            for (SysDept allDept : allDepts) {
                if (parentDept.getId() != null && parentDept.getId().equals(allDept.getParentId())){
                    allDept.setParentName(parentDept.getName());
                    allDept.setLevel(parentDept.getLevel() + 1);
                    children.add(allDept);
                }
            }
            parentDept.setChildren(children);
            findChildren(children, allDepts);
        }
    }

    @Override
    public int save(SysDept record) {
        if (record.getId() == null || record.getId() == 0) {
            return deptMapper.insertSelective(record);
        }
        return deptMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteById(SysDept record) {
        return deptMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysDept> records) {
        for (SysDept record : records) {
            deleteById(record);
        }
        return 1;
    }

    @Override
    public SysDept findById(Long id) {
        return deptMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, deptMapper);
    }
}
