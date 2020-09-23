package com.lwg.mango.admin.mapper;

import com.lwg.mango.admin.pojo.SysMenu;
import com.lwg.mango.admin.pojo.SysMenuExample;
import com.lwg.mango.admin.pojo.SysRole;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysMenuMapper {
    long countByExample(SysMenuExample example);

    int deleteByExample(SysMenuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    List<SysMenu> selectByExample(SysMenuExample example);

    SysMenu selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    int updateByExample(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    List<SysMenu> findMenuWithRoles();

    List<SysMenu> findByUserName(@Param("name") String name);

    List<SysMenu> findRoleMenus(@Param("id") Long id);

    List<SysMenu> findAll();
}