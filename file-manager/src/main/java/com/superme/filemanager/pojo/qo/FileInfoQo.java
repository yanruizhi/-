package com.superme.filemanager.pojo.qo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传请求参数
 * 作者: yanruizhi
 * 时间: 2023/8/7 14:50
 */
@Data
public class FileInfoQo {
    private String description;
    private MultipartFile file;
}
