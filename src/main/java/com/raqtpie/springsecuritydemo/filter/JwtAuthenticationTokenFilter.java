package com.raqtpie.springsecuritydemo.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.raqtpie.springsecuritydemo.domain.LoginUser;
import com.raqtpie.springsecuritydemo.service.TokenBlackListService;
import com.raqtpie.springsecuritydemo.service.impl.UserDetailsServiceImpl;
import com.raqtpie.springsecuritydemo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private TokenBlackListService tokenBlackListService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (StrUtil.isBlank(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (!JwtUtil.isTokenEffective(token)) {
            authenticationEntryPoint.commence(request, response, new BadCredentialsException("token已失效"));
            return;
        }
        if (tokenBlackListService.isTokenBlacklisted(token)) {
            authenticationEntryPoint.commence(request, response, new BadCredentialsException("token已失效"));
            return;
        }
        if (JwtUtil.isTokenExpired(token)) {
            authenticationEntryPoint.commence(request, response, new BadCredentialsException("token已过期"));
            return;
        }
        String userJson = JwtUtil.extractSubject(token);
        LoginUser loginUser = JSONUtil.toBean(userJson, LoginUser.class);
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginUser.getUsername());
        loginUser = (LoginUser) userDetails;
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }
}
