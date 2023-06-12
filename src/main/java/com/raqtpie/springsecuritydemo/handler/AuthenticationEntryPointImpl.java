package com.raqtpie.springsecuritydemo.handler;

import cn.hutool.json.JSONUtil;
import com.raqtpie.springsecuritydemo.common.ResponseResult;
import com.raqtpie.springsecuritydemo.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    /**
     * 当用户尝试访问需要权限才能的REST资源而不提供Token或者Token错误时，将调用此方法发送401响应以及错误信息
     * @param request 未经身份验证的请求
     * @param response 用户代理可以开始身份验证
     * @param authException 包含原因的异常
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult<Object> result = ResponseResult.unauthorized("用户未登录或token已失效");
        String json = JSONUtil.parse(result).toStringPretty();
        WebUtils.renderString(response, json);
    }
}
