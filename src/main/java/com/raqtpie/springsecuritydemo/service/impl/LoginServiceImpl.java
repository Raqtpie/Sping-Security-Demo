package com.raqtpie.springsecuritydemo.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.raqtpie.springsecuritydemo.dao.UserDao;
import com.raqtpie.springsecuritydemo.domain.LoginUser;
import com.raqtpie.springsecuritydemo.service.LoginService;
import com.raqtpie.springsecuritydemo.common.ResponseResult;
import com.raqtpie.springsecuritydemo.domain.User;
import com.raqtpie.springsecuritydemo.service.TokenBlackListService;
import com.raqtpie.springsecuritydemo.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenBlackListService tokenBlackListService;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Override
    public ResponseResult<Map<String, String>> login(User user) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationConfiguration.getAuthenticationManager().authenticate(authenticationToken);

        if (authenticate == null) {
            throw new RuntimeException("登录失败");
        }

        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        loginUser.getUser().setPassword(null);
        String userJson = JSONUtil.parseObj(loginUser).toString();
        String token = JwtUtil.generateToken(userJson);
        log.info("token为{}", token);

        Map<String, String> map = new HashMap<>();
        map.put("token", token);

        return ResponseResult.success(map, "登录成功");
    }

    @Override
    public ResponseResult<String> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        tokenBlackListService.addTokenToBlackList(token);
        return ResponseResult.success("注销成功");
    }

    @Override
    public ResponseResult<String> addUser(User user) {
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        userDao.insert(user);
        User userTemp = userDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, user.getUsername()));
        Long userId = userTemp.getId();
        userDao.addRoleWithUserId(userId, 1L);
        return ResponseResult.success("注册成功成功");
    }
}
