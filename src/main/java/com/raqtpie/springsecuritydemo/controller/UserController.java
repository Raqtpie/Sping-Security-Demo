package com.raqtpie.springsecuritydemo.controller;

import com.raqtpie.springsecuritydemo.common.ResponseResult;
import com.raqtpie.springsecuritydemo.domain.User;
import com.raqtpie.springsecuritydemo.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/addUser")
    public ResponseResult<String> addUser(@RequestBody User user) {
        return loginService.addUser(user);
    }

    @PostMapping("/login")
    public ResponseResult<Map<String, String>> login(@RequestBody User user) throws Exception {
        log.info("进入方法");
        return loginService.login(user);
    }

    @GetMapping("/logout")
    public ResponseResult<String> logout(HttpServletRequest request) {
        return loginService.logout(request);
    }
}
