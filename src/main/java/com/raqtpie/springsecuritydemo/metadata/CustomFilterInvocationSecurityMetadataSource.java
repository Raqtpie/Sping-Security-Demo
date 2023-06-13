package com.raqtpie.springsecuritydemo.metadata;

import cn.hutool.core.text.AntPathMatcher;
import com.raqtpie.springsecuritydemo.dao.PermissionDao;
import com.raqtpie.springsecuritydemo.domain.Permission;
import com.raqtpie.springsecuritydemo.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private PermissionDao permissionDao;

    /**
     * 获取当前请求所需要的角色
     * @param object 当前请求对象
     * @return 当前请求所需要的角色
     * @throws IllegalArgumentException 参数异常
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 获取当前请求的url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();

        // 获取所有的资源
        List<Permission> allPermission = permissionDao.getAllPermission();

        // 遍历所有的资源，获取当前请求url所需要的角色
        for (Permission permission : allPermission) {
            if (antPathMatcher.match(permission.getUrl(), requestUrl)) {
                List<Role> roles = permission.getRoles();
                String[] roleArr = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    roleArr[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(roleArr);
            }
        }

        // 没有匹配上的资源，都是登录访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    /**
     * 返回所有定义好的权限资源，Spring Security在启动时会校验相关配置是否正确，不需要校验返回null即可
     * @return 所有定义好的权限资源
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        // 不需要校验则直接返回null
        return null;
    }

    /**
     * 返回类对象是否支持校验，web项目一般使用FilterInvocation来判断，或者直接返回true
     * @param clazz 类对象
     * @return 是否支持校验
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
