package com.lwg.mango.admin.service;

import java.util.List;

import com.lwg.mango.admin.pojo.SysDept;
import com.lwg.mango.core.service.CurdService;

public interface DeptService extends CurdService<SysDept> {

    List<SysDept> findTree();
}
