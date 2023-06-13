package com.raqtpie.springsecuritydemo.controller;

import com.raqtpie.springsecuritydemo.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api")
public class ApiController {
    @GetMapping("/resource1")
    public ResponseResult<String> resource1() {
        return ResponseResult.success("访问资源1成功");
    }

    @GetMapping("/resource2")
    public ResponseResult<String> resource2() {
        return ResponseResult.success("访问资源2成功");
    }

    @GetMapping("/resource3")
    public ResponseResult<String> resource3() {
        return ResponseResult.success("访问资源3成功");
    }
}
