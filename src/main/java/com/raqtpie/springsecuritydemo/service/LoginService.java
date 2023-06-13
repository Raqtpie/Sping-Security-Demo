package com.raqtpie.springsecuritydemo.service;

import cn.hutool.http.server.HttpServerRequest;
import com.raqtpie.springsecuritydemo.common.ResponseResult;
import com.raqtpie.springsecuritydemo.domain.User;
import org.springframework.http.HttpRequest;

import java.util.Map;

public interface LoginService {
    ResponseResult<Map<String, String>> login(User user) throws Exception;
    ResponseResult<String> logout(HttpServerRequest request);
}
