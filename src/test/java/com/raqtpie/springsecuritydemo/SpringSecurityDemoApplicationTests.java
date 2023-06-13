package com.raqtpie.springsecuritydemo;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.raqtpie.springsecuritydemo.dao.PermissionDao;
import com.raqtpie.springsecuritydemo.dao.RoleDao;
import com.raqtpie.springsecuritydemo.domain.LoginUser;
import com.raqtpie.springsecuritydemo.domain.Permission;
import com.raqtpie.springsecuritydemo.domain.Role;
import com.raqtpie.springsecuritydemo.domain.User;
import com.raqtpie.springsecuritydemo.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class SpringSecurityDemoApplicationTests {
    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Test
    void contextLoads() {
    }

    @Test
    void loadPassword() {
        System.out.println(new BCryptPasswordEncoder().encode("password1"));
        System.out.println(new BCryptPasswordEncoder().encode("password2"));
        System.out.println(new BCryptPasswordEncoder().encode("password3"));
    }

    @Test
    void testGetAllPermission() {
        List<Permission> permissions = permissionDao.getAllPermission();
        System.out.println(permissions);
    }

    @Test
    void testGetListByUserId() {
        List<Role> listByUserId = roleDao.getListByUserId(1L);
        System.out.println(listByUserId);
    }

    @Test
    void loadToken() throws Exception {
        User user = new User();
        user.setUsername("user1");
        user.setPassword("password1");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationConfiguration.getAuthenticationManager().authenticate(authenticationToken);

        if (authenticate == null) {
            throw new RuntimeException("登录失败");
        }

        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        loginUser.getUser().setPassword(null);
        String userJson = JSONUtil.parseObj(loginUser).toString();
        String token = JwtUtil.generateToken(userJson);
        System.out.println(token);
    }

    @Test
    void parseJWT() {
        String s = JwtUtil.extractSubject("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJlOWNmNTExNS03YWViLTQ2Y2YtYjk0ZS1lMjFhNTBiMGZjNTgiLCJzdWIiOiJ7XCJ1c2VyXCI6e1wiaWRcIjoxLFwidXNlcm5hbWVcIjpcInVzZXIxXCIsXCJzdGF0dXNcIjowLFwiZGVsRmxhZ1wiOjB9LFwicm9sZXNcIjpbe1wiaWRcIjoxLFwibmFtZVwiOlwiUk9MRV9yb2xlMVwifV19IiwiaWF0IjoxNjg2NjU5NTMyLCJpc3MiOiJSYXF0cGllIiwiZXhwIjoxNjg3NTIzNTMyfQ.YoIGSzcvy9VGVAkmuJEOx1btRZtwSbHo2b27ysulABk");
        System.out.println(s);
    }
}
