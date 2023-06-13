package com.raqtpie.springsecuritydemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.raqtpie.springsecuritydemo.domain.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleDao extends BaseMapper<Role> {
}
