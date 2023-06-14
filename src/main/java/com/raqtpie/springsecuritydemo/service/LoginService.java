package com.raqtpie.springsecuritydemo.service;

import com.raqtpie.springsecuritydemo.common.ResponseResult;
import com.raqtpie.springsecuritydemo.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface LoginService {
    ResponseResult<Map<String, String>> login(User user) throws Exception;
    ResponseResult<String> logout(HttpServletRequest request);
}
