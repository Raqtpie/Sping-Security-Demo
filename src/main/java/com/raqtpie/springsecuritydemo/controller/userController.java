package com.raqtpie.springsecuritydemo.controller;

import com.raqtpie.springsecuritydemo.common.ResponseResult;
import com.raqtpie.springsecuritydemo.domain.User;
import com.raqtpie.springsecuritydemo.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/user")
public class userController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseResult<String> login(User user) throws Exception {
        return loginService.login(user);
    }
}
