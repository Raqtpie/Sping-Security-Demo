package com.raqtpie.springsecuritydemo.service.impl;

import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.json.JSONUtil;
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
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
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
    public ResponseResult<String> logout(HttpServerRequest request) {
        String token = request.getHeader("Authorization");
        tokenBlackListService.addTokenToBlackList(token);
        return ResponseResult.success("注销成功");
    }
}
