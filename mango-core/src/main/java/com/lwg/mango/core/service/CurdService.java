package com.lwg.mango.core.service;

import com.lwg.mango.core.page.PageRequest;
import com.lwg.mango.core.page.PageResult;
import java.util.List;

/*
    通用接口 封装了增删改查
 */
public interface CurdService<T> {
    /*
    保存操作
     */
    int save(T record);

    /*
    删除操作
     */
    int deleteById(T record);


    /*
    批量删除操作
     */

    int delete(List<T> records);

    /*
    根据ID进行查询
     */
    T findById(Long id);

    /*
    分页查询
     */

    PageResult findPage(PageRequest pageRequest);


}
