package com.lwg.mango.admin.service.impl;

import com.lwg.mango.admin.mapper.SysMenuMapper;
import com.lwg.mango.admin.mapper.SysUserMapper;
import com.lwg.mango.admin.pojo.SysMenu;
import com.lwg.mango.admin.pojo.SysUser;
import com.lwg.mango.admin.service.UserService;
import com.lwg.mango.common.utils.DateTimeUtils;
import com.lwg.mango.common.utils.PoiUtils;
import com.lwg.mango.core.page.MybatisPageHelper;
import com.lwg.mango.core.page.PageRequest;
import com.lwg.mango.core.page.PageResult;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public List<SysUser> findAll() {
        return userMapper.findAll();
    }

    @Override
    public SysUser findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public Set<String> findPermissions(String name) {
        Set<String> perms = new HashSet<>();
        List<SysMenu> sysMenus = menuMapper.findByUserName(name);
        for (SysMenu sysMenu : sysMenus) {
            if (sysMenu.getPerms() != null && !"".equals(sysMenu.getPerms())) {
                perms.add(sysMenu.getPerms());
            }
        }
        return perms;
    }

    @Override
    public PageResult findPageUserRoles(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, userMapper, "findPageUserRoles");
    }


    @Override
    public int save(SysUser record) {
        if (record.getId() == null || record.getId() == 0) {
            return userMapper.insertSelective(record);
        } else {
            return userMapper.updateByPrimaryKeySelective(record);
        }
    }

    @Override
    public int deleteById(SysUser record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(List<SysUser> records) {
        for (SysUser record : records) {
            deleteById(record);
        }
        return 1;
    }

    @Override
    public SysUser findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, userMapper);
    }

    @Override
    public File createUserExcelFile(PageRequest pageRequest) {
        //分页查询
        PageResult pageResult = findPage(pageRequest);
        return createUserExcelFile(pageResult.getContent());
    }

    public static File createUserExcelFile(List<?> records) {
        if (records == null) {
            records = new ArrayList<>();
        }
        //工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        //工作表
        XSSFSheet sheet = workbook.createSheet();
        //行
        XSSFRow row0 = sheet.createRow(0);

        //工作表的第一行
        int columnIndex = 0;
        //单元格  创建每一个单元格的数据
        row0.createCell(columnIndex).setCellValue("No");
        row0.createCell(++columnIndex).setCellValue("ID");
        row0.createCell(++columnIndex).setCellValue("用户名");
        row0.createCell(++columnIndex).setCellValue("昵称");
        row0.createCell(++columnIndex).setCellValue("机构");
        row0.createCell(++columnIndex).setCellValue("角色");
        row0.createCell(++columnIndex).setCellValue("邮箱");
        row0.createCell(++columnIndex).setCellValue("手机号");
        row0.createCell(++columnIndex).setCellValue("状态");
        row0.createCell(++columnIndex).setCellValue("头像");
        row0.createCell(++columnIndex).setCellValue("创建人");
        row0.createCell(++columnIndex).setCellValue("创建时间");
        row0.createCell(++columnIndex).setCellValue("最后更新人");
        row0.createCell(++columnIndex).setCellValue("最后更新时间");

        //将查询的数据循环遍历出来
        for (int i = 0; i < records.size(); i++) {
            SysUser user = (SysUser) records.get(i);
            //设置行数从第二行开始 依次创立每行的单元格
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < columnIndex + 1; j++) {
                row.createCell(j);
            }
            //从第一列开始
            columnIndex = 0;
            row.getCell(columnIndex).setCellValue(i+1);
            row.getCell(++columnIndex).setCellValue(user.getId());
            row.getCell(++columnIndex).setCellValue(user.getName());
            row.getCell(++columnIndex).setCellValue(user.getNickName());
            row.getCell(++columnIndex).setCellValue(user.getDeptName());
            row.getCell(++columnIndex).setCellValue(user.getRoleNames());
            row.getCell(++columnIndex).setCellValue(user.getEmail());
            row.getCell(++columnIndex).setCellValue(user.getStatus());
            row.getCell(++columnIndex).setCellValue(user.getAvatar());
            row.getCell(++columnIndex).setCellValue(user.getCreateBy());
            row.getCell(++columnIndex).setCellValue(DateTimeUtils.getDateTime(user.getCreateTime()));
            row.getCell(++columnIndex).setCellValue(user.getLastUpdateBy());
            row.getCell(++columnIndex).setCellValue(DateTimeUtils.getDateTime(user.getLastUpdateTime()));
        }
        return PoiUtils.createExcelFile(workbook,"download_user");
    }
}
