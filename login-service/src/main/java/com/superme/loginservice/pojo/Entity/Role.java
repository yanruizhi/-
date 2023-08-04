package com.superme.loginservice.pojo.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 权限信息
 * 作者: yanruizhi
 * 时间: 2023/8/4 16:21
 */
@Data
@TableName("role")
public class Role {
    @TableId(type = IdType.AUTO)
    private String id;
    @TableField("name")
    private String name;
    private String code;
    private String description;
}
