package com.raqtpie.springsecuritydemo.service.impl;

import com.raqtpie.springsecuritydemo.service.LoginService;
import com.raqtpie.springsecuritydemo.common.ResponseResult;
import com.raqtpie.springsecuritydemo.domain.User;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public ResponseResult<String> login(User user) throws Exception {
        return null;
    }

    @Override
    public ResponseResult<String> logout() {
        return null;
    }
}
