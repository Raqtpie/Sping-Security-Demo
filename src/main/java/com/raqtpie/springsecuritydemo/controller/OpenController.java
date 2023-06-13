package com.raqtpie.springsecuritydemo.controller;

import com.raqtpie.springsecuritydemo.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/open")
public class OpenController {
    @GetMapping("/resource")
    public ResponseResult<String> resource() {
        return ResponseResult.success("访问资源成功");
    }
}
