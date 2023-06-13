package com.raqtpie.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.raqtpie.springsecuritydemo.dao.RoleDao;
import com.raqtpie.springsecuritydemo.dao.UserDao;
import com.raqtpie.springsecuritydemo.domain.LoginUser;
import com.raqtpie.springsecuritydemo.domain.Role;
import com.raqtpie.springsecuritydemo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername, username);
        User user = userDao.selectOne(userLambdaQueryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<Role> roles = roleDao.getListByUserId(user.getId());
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(user);
        loginUser.setRoles(roles);
        return loginUser;
    }
}
