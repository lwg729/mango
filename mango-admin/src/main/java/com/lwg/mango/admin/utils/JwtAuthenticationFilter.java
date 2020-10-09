package com.lwg.mango.admin.utils;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * 权限认证过滤器
 * 负责登录认证时检查并生产令牌保存到上下文,
 * 就扣权限认证过程中,系统从上下文获取令牌校验接口获得权限
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public void doFilterInternal(HttpServletResponse response, HttpServletRequest request, FilterChain chain) throws IOException,
            ServletException {

        //获取token 并检查登录状态
        SecurityUtils.checkAuthentication(request);
        chain.doFilter(request, response);
    }
}
