package com.superme.filemanager.pojo.qo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * 描述: 添加存档信息条件
 * 作者: yanruizhi
 * 时间: 2024/4/10 15:46
 */
@Data
public class AddSaveDataInfoQO implements Serializable {
    private String gameName;
    private String description;
    private MultipartFile file;
    private String type;

}
