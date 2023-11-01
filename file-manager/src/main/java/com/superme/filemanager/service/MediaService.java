package com.superme.filemanager.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 描述: 媒资服务
 * 作者: yanruizhi
 * 时间: 2023/10/31 10:07
 */
public interface MediaService {
    /**
     * 保存媒资信息
     * @param files 文件列表
     */
    Integer saveMedia(List<MultipartFile> files);
}
