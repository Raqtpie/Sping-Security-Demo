package com.raqtpie.springsecuritydemo.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.raqtpie.springsecuritydemo.dao.UserDao;
import com.raqtpie.springsecuritydemo.domain.LoginUser;
import com.raqtpie.springsecuritydemo.domain.User;
import com.raqtpie.springsecuritydemo.service.TokenBlackListService;
import com.raqtpie.springsecuritydemo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private UserDao userDao;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (tokenBlackListService.isTokenBlacklisted(token)) {
            throw new RuntimeException("token已失效");
        }
        if (!JwtUtil.isTokenEffective(token)) {
            throw new RuntimeException("token非法");
        }
        if (StrUtil.isBlank(token) || JwtUtil.isTokenExpired(token)) {
            throw new RuntimeException("用户未登录或token已过期");
        }
        String userJson = JwtUtil.extractSubject(token);
        User user = JSONUtil.toBean(userJson, User.class);
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        user = userDao.selectOne(userLambdaQueryWrapper);
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(user);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }
}
