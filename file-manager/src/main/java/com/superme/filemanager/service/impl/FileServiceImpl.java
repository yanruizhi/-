package com.superme.filemanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.superme.common.beans.PageRequest;
import com.superme.common.beans.PageResponse;
import com.superme.common.beans.Result;
import com.superme.common.exceptions.FileException;
import com.superme.common.utils.ParameterCheckUtil;
import com.superme.filemanager.mapper.FileMapper;
import com.superme.filemanager.pojo.Entity.FileInfo;
import com.superme.filemanager.service.FilesService;
import com.superme.filemanager.utils.FileUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * FileServiceImpl
 * 作者: yanruizhi
 * 时间: 2023/8/7 10:24
 */
@Service
public class FileServiceImpl implements FilesService {

    private final String basePath = System.getProperty("user.dir") + "/file"; //jar包所在目录下新建文件夹file

    @Resource
    private FileMapper fileMapper;

    /**
     * 查询文件列表
     */
    @Override
    public Result<PageResponse<FileInfo>> getPage(PageRequest page) {
        ParameterCheckUtil.checkPage(page);
        Page<FileInfo> fileInfoPage = fileMapper.selectPage(new Page<>(page.getCurrentPage(), page.getPageSize()), null);
        return Result.OK(new PageResponse<>(fileInfoPage));
    }

    /**
     * 单文件上传
     */
    @Override
    public Result<Object> uploadSingle(MultipartFile file, String description) {
        ParameterCheckUtil.checkNull(file, "文件为空,请上传文件");
        String originalFilename = file.getOriginalFilename();//完整文件名
        //根据文件类型对文件进行分类,以文件夹分类
        assert originalFilename != null;
        String postfix = originalFilename.substring(file.getOriginalFilename().lastIndexOf(".") + 1);//文件后缀类型
        //判断是否有当前类型的文件夹
        String savePath = basePath + "/" + postfix;
        File dir = new File(savePath);
        //不存在则创建目录
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }
        //判断文件是否存在.不存在则保存
        File exist = new File(dir + "/" + originalFilename);
        if (exist.exists()) {
            throw new FileException("文件已存在,请更换文件名或删除原文件");
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

    /**
     * 下载文件
     */
    @Override
    public Result<Object> download(String id, HttpServletResponse response) throws IOException {
        ParameterCheckUtil.checkNull(id, "文件id不能为空");
        //查询文件信息
        FileInfo fileInfo = fileMapper.selectById(id);
        ParameterCheckUtil.checkNull(fileInfo, "文件信息不存在,请刷新后再试");
        String url = fileInfo.getUrl();
        ParameterCheckUtil.checkNull(url, "url信息为空,文件信息数据异常");
        File file = new File(url);
        if (!file.exists() || file.isDirectory()) {
            throw new FileException("文件已被删除");
        }
        try (FileInputStream fileInputStream = new FileInputStream(file); ServletOutputStream outputStream = response.getOutputStream()) {
            //            response.setContentType("application/octet-stream");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);//以文件形式返回浏览器,否则浏览器悔自动解析一些能打开的格式
            // 如果文件名为中文需要设置编码
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileInfo.getName(), "utf8"));
            // 返回前端文件名需要添加
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");

            byte[] bytes = new byte[1024 * 8];
            int len;
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
        }
        return Result.OK();
    }

}
