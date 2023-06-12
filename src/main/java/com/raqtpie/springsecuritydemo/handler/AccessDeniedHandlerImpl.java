package com.raqtpie.springsecuritydemo.handler;

import cn.hutool.json.JSONUtil;
import com.raqtpie.springsecuritydemo.common.ResponseResult;
import com.raqtpie.springsecuritydemo.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    /**
     * 当用户尝试访问需要权限才能的REST资源而权限不足的时候，将调用此方法发送403响应以及错误信息
     * @param request 未经身份验证的请求
     * @param response 用户代理可以开始身份验证
     * @param accessDeniedException 包含原因的异常
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult<Object> result = ResponseResult.forbidden("权限不足");
        String json = JSONUtil.parse(result).toStringPretty();
        WebUtils.renderString(response, json);
    }
}
