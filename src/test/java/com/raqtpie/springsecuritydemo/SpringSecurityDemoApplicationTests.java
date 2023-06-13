package com.raqtpie.springsecuritydemo;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.raqtpie.springsecuritydemo.dao.PermissionDao;
import com.raqtpie.springsecuritydemo.dao.RoleDao;
import com.raqtpie.springsecuritydemo.domain.Permission;
import com.raqtpie.springsecuritydemo.domain.Role;
import com.raqtpie.springsecuritydemo.domain.User;
import com.raqtpie.springsecuritydemo.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class SpringSecurityDemoApplicationTests {
    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RoleDao roleDao;

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
    void loadToken() {
        User user = new User();
        user.setUsername("user1");
        user.setPassword("password1");
        JSON json = JSONUtil.parse(user);
        String jsonString = json.toString();
        String token = JwtUtil.generateToken(jsonString);
        System.out.println(token);
    }
}
