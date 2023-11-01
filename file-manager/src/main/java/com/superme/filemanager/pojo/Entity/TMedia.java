package com.superme.filemanager.pojo.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 媒资信息表对应实体类
 */
@Data
@TableName("t_media")
public class TMedia{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private String id;

    /**
     * 名称
     */
    @TableField(value = "media_name")
    private String mediaName;

    /**
     * 资源类型
     */
    @TableField(value = "media_type")
    private String mediaType;

    /**
     * 后缀
     */
    private String mediaSuffix;

    /**
     * 拍摄时间
     */
    private LocalDateTime shootingTime;

    /**
     * 经度
     */
    @TableField(value = "longitude")
    private String longitude;

    /**
     * 纬度
     */
    @TableField(value = "latitude")
    private String latitude;

    /**
     * 海拔高度
     */
    @TableField(value = "altitude")
    private String altitude;

    /**
     * 字节数
     */
    @TableField(value = "size")
    private Long size;
    /**
     * 视频时长/音频时长,
     */
    private String durationTime;

}