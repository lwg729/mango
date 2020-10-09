package com.lwg.mango.admin.security;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lwg.mango.admin.pojo.SysUser;
import com.lwg.mango.admin.service.impl.UserServiceImpl;

/**
 * 从数据库中获得登录的用户信息 实现UserDetailsService接口 覆写其中的方法loadUserByUsername,
 * 查询用户的密码信息和权限信息并封装到userDetails的实现类对象,
 * 作为结果JwtUsertails返回给DaoAuthenticationProvider做进一步处理
 * 传递过程  UserDetailsServiceImpl覆写loadUserByUsername查询信息->结果封装到JwtUserDetails->返回给DaoAuthenticationProvider
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.findByName(username);
        if (user==null){
            throw new UsernameNotFoundException("该用户不存在");
        }

        //用户权限列表 根据权限标识如@PreAuthorize("hasAuthrity('sys:menu:view')")
        //标准的接口对比,决定是否可以调用接口 获得用户菜单集合
        Set<String> permissions = userService.findPermissions(username);

        //获取permissions中所有权限标识额集合
        List<GrantedAuthority> grantedAuthorities = permissions.stream()
                .map(GrantAuthorityImpl::new).collect(Collectors.toList());
        return new JwtUserDetails(user.getName(),user.getPassword(),user.getSalt(),grantedAuthorities);
    }
}
