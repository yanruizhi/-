package com.superme.filemanager.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 文件信息实体类
 * 作者: yanruizhi
 * 时间: 2023/8/7 14:25
 */
@Data
@TableName("file_info")
public class FileInfo {
    @TableId(type = IdType.AUTO)
    private String id;
    private String name;
    private String type;
    private String url;
    private Long size;
    private String description;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    private String userId;
}
