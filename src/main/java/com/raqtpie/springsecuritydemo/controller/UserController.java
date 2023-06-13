package com.raqtpie.springsecuritydemo.controller;

import cn.hutool.http.server.HttpServerRequest;
import com.raqtpie.springsecuritydemo.common.ResponseResult;
import com.raqtpie.springsecuritydemo.domain.User;
import com.raqtpie.springsecuritydemo.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseResult<Map<String, String>> login(User user) throws Exception {
        log.info("进入方法");
        return loginService.login(user);
    }

    @GetMapping("/logout")
    public ResponseResult<String> logout(HttpServerRequest request) {
        return loginService.logout(request);
    }
}
