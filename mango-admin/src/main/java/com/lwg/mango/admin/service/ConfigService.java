package com.lwg.mango.admin.service;


import java.util.List;

import com.lwg.mango.admin.pojo.SysConfig;
import com.lwg.mango.core.service.CurdService;

public interface ConfigService extends CurdService<SysConfig> {

    //根据标签查询
    public List<SysConfig> findByLabel(String label);

}
