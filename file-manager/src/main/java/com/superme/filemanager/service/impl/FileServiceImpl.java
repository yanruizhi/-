package com.superme.filemanager.service.impl;

import com.superme.common.beans.Result;
import com.superme.common.utils.ParameterCheckUtil;
import com.superme.filemanager.mapper.FileMapper;
import com.superme.filemanager.pojo.Entity.FileInfo;
import com.superme.filemanager.service.FileService;
import com.superme.filemanager.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * FileServiceImpl
 * 作者: yanruizhi
 * 时间: 2023/8/7 10:24
 */
@Service
public class FileServiceImpl implements FileService {

    private final String basePath = System.getProperty("user.dir")+"/file"; //jar包所在目录

    @Resource
    private FileMapper fileMapper;
    /**
     * 单文件上传
     */
    @Override
    public Result<Object> uploadSingle(MultipartFile file, String description) {
        ParameterCheckUtil.checkNull(file, "文件为空,请上传文件");
        String originalFilename = file.getOriginalFilename();//完整文件名
        //根据文件类型对文件进行分类,以文件夹分类
        String postfix = originalFilename.substring(file.getOriginalFilename().lastIndexOf(".")+1);//文件后缀类型
        //判断是否有当前类型的文件夹
        String savePath = basePath + "/" + postfix;
        File dir = new File(savePath);
        //不存在则创建目录
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }
        //保存文件
        String save = FileUtil.save(file, savePath);
        //储存文件信息
        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(originalFilename);
        fileInfo.setType(postfix);
        fileInfo.setUrl(dir + "/" + originalFilename);
        fileInfo.setSize(file.getSize());
        fileInfo.setDescription(description);
        fileInfo.setUserId(null);
        fileMapper.insert(fileInfo);
        return Result.OK(save);
    }
    /**
     * 多文件上传
     */
    @Override
    public Result<Object> uploadMulti(List<MultipartFile> files, String description) {
        ParameterCheckUtil.checkNull(files, "文件列表为空,请上传文件");
        files.forEach(file -> this.uploadSingle(file, description));
        return Result.OK("上传成功");
    }
}
