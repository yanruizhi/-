package com.superme.filemanager.pojo.entity;

import lombok.Data;

/**
 * 描述: TODO
 * 作者: yanruizhi
 * 时间: 2024/4/10 15:38
 */
@Data
public class SaveDataInfo {
    private String id;
    /**
     * 游戏名称
     */
    private String gameName;
    /**
     * 描述
     */
    private String description;
    /**
     * 文件地址
     */
    private String fileUrl;
    /**
     * 文件后缀
     */
    private String suffix;
    /**
     * 类型: 1官方 2同人
     */
    private String type;
}
