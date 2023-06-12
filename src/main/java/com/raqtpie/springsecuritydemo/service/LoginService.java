package com.raqtpie.springsecuritydemo.service;

import com.raqtpie.springsecuritydemo.common.ResponseResult;
import com.raqtpie.springsecuritydemo.domain.User;

public interface LoginService {
    ResponseResult<String> login(User user) throws Exception;
    ResponseResult<String> logout();
}
