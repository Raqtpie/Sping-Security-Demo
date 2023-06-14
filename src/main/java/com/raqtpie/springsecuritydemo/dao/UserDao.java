package com.raqtpie.springsecuritydemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.raqtpie.springsecuritydemo.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
    void addRoleWithUserId(Long userId, Long roleId);
}
