package com.superme.filemanager.controller;

import com.alibaba.fastjson.JSON;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.superme.common.exceptions.ParameterException;
import com.superme.filemanager.enums.DirectoryEnum;
import com.superme.filemanager.service.MediaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
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



    @PostMapping("test")
    public void test(MultipartFile multipartFile) {
        File file = null;
        Metadata metadata = null;
        File directory = new File(DirectoryEnum.USER_DIR.getName() + "\\" + DirectoryEnum.PHOTOS.getName());
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String[] filename = originalFilename.split("\\.");
            file = File.createTempFile(filename[0], filename[1], directory);
            multipartFile.transferTo(file);
            //jvm退出时删除零时文件
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        File file1 = FileUtil.multipartFileToFile(DirectoryEnum.USER_DIR.getName()+"\\"+DirectoryEnum.PHOTOS.getName(), file);
        try {
            metadata = ImageMetadataReader.readMetadata(file);

        } catch (ImageProcessingException | IOException e) {
            e.printStackTrace();
        }
        if (metadata == null) {
            throw new ParameterException("系统异常");
        }
        System.err.println(JSON.toJSONString(metadata));
        for (Directory d : metadata.getDirectories()) {

            for (Tag tag : d.getTags()) {
                String tagName = tag.getTagName();  //标签名
                String desc = tag.getDescription(); //标签信息
                System.out.println(tagName + "===" + desc);//照片信息
            }
        }
    }

}
