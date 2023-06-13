package com.raqtpie.springsecuritydemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.raqtpie.springsecuritydemo.domain.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleDao extends BaseMapper<Role> {
    List<Role> getListByUserId(Long userId);
}
