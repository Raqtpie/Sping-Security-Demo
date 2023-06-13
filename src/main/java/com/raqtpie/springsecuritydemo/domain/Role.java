package com.raqtpie.springsecuritydemo.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "role")
public class Role implements Serializable {
    private static final long serialVersionUID = 54123124465465421L;

    /**
     * 主键 id
     */
    @TableId(value = "user_id")
    private Long userId;
    /**
     * 角色名称
     */
    private String name;
}
