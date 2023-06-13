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
 * 权限表(Permission)实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "permission")
public class Permission implements Serializable {
    private static final long serialVersionUID = 54123124465465421L;

    /**
     * 主键 id
     */
    @TableId
    private Long id;
    /**
     * url
     */
    private String url;
    /**
     * 角色
     */
    @TableField(exist = false)
    private List<Role> roles;
}
