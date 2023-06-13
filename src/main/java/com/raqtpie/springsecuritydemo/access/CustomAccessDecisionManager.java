package com.raqtpie.springsecuritydemo.access;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {
    /**
     * 判断当前登录的用户是否具备当前请求URL所需要的角色信息
     * @param authentication 当前登录的用户信息，包含当前用户的角色信息
     * @param object 当前请求的资源对象
     * @param configAttributes 当前请求资源所需要的角色信息
     * object being invoked
     * @throws AccessDeniedException 用户没有权限访问
     * @throws InsufficientAuthenticationException 需要进一步认证
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        // FIXME:authorities.authorities为空
        // 当前请求需要的角色信息
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // 当前登录用户所具备的角色信息和当前请求所需要的角色信息进行对比
        for (ConfigAttribute configAttribute : configAttributes) {
            // 当前请求需要的角色信息为ROLE_LOGIN，表示当前请求需要登录后才能访问
            if ("ROLE_LOGIN".equals(configAttribute.getAttribute()) && authentication instanceof AnonymousAuthenticationToken) {
                throw new AccessDeniedException("尚未登录，请登录");
            }

            // 当前请求需要的角色信息和当前登录用户所具备的角色信息进行对比
            for (GrantedAuthority authority : authorities) {
                if (configAttribute.getAttribute().equals(authority.getAuthority())) {
                    return;
                }
            }
        }

        // 当前登录用户所具备的角色信息和当前请求所需要的角色信息没有匹配上，抛出异常
        throw new AccessDeniedException("权限不足，请联系管理员");
    }

    /**
     * 表示当前AccessDecisionManager是否支持当前ConfigAttribute
     * @param attribute 当前请求所需要的角色信息
     * <code>AbstractSecurityInterceptor</code>
     * @return true 支持当前ConfigAttribute，false 不支持当前ConfigAttribute
     */
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    /**
     * 表示当前AccessDecisionManager是否支持当前被保护的对象
     * @param clazz 被保护的对象
     * @return true 支持当前被保护的对象，false 不支持当前被保护的对象
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
