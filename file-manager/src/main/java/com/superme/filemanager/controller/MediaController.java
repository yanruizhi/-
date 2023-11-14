package com.superme.filemanager.controller;

import com.superme.filemanager.service.MediaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述: 媒资管理前端控制器
 * 作者: yanruizhi
 * 时间: 2023/10/31 10:06
 */
@RestController
@RequestMapping("media")
public class MediaController {

    @Resource
    private MediaService mediaService;

    /**
     * 保存媒资信息
     * @param files 文件列表
     */
    @PostMapping("save")
    public Integer saveMedia(List<MultipartFile> files) {
        return mediaService.saveMedia(files);
    }



}
