package com.raqtpie.springsecuritydemo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表(User)实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = 54123124465465412L;

    /**
     * 主键 id
     */
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 密码
     */
    private String password;
    /**
     * 账号状态（0正常 1停用）
     */
    private String status;
    /**
     * 邮箱
     */
    private Date CreateTime;
    /**
     * 手机号
     */
    private Date updateTime;
    /**
     * 是否注销（0正常 1注销）
     */
    private Integer delFlag;
}
