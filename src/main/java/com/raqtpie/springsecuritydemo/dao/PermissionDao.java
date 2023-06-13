package com.raqtpie.springsecuritydemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.raqtpie.springsecuritydemo.domain.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionDao extends BaseMapper<Permission> {
    List<Permission> getAllPermission();
}
