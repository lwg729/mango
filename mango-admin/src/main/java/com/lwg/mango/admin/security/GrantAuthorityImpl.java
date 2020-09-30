package com.lwg.mango.admin.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * 权限的封装  内部包含一个字符串类型的权限标识 authority,对应菜单表中的perms字段的权限字符串
 * 比如用户管理的增删改查权限标识 sys:user:view等
 */
public class GrantAuthorityImpl implements GrantedAuthority {

    private String authority;

    public GrantAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return  this.authority;
    }
}
