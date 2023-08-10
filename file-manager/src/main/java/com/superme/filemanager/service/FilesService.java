package com.superme.filemanager.service;

import com.superme.common.beans.PageRequest;
import com.superme.common.beans.PageResponse;
import com.superme.common.beans.Result;
import com.superme.filemanager.pojo.Entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * FileService
 * 作者: yanruizhi
 * 时间: 2023/8/7 10:23
 */
public interface FilesService {
    /**
     * 单文件上传
     */
    Result<Object> uploadSingle(MultipartFile file, String description);
    /**
     * 多文件上传
     */
    Result<Object> uploadMulti(List<MultipartFile> files, String description);
    /**
     * 下载文件
     */
    Result<Object> download(String id, HttpServletResponse response) throws IOException;
    /**
     * 查询文件列表
     */
    Result<PageResponse<FileInfo>> getPage(PageRequest page);
}
