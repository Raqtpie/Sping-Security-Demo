package com.raqtpie.springsecuritydemo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 用户表(User)实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 54123124465465412L;

    /**
     * 主键 id
     */
    @TableId
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 账号状态（0正常 1停用）
     */
    private Integer status;
    /**
     * 是否注销（0正常 1注销）
     */
    private Integer delFlag;
    /**
     * 角色
     */
    @TableField(exist = false)
    private List<Role> roles;
}
