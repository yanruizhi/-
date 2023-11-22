package com.superme.content.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 描述: TODO
 * 作者: yanruizhi
 * 时间: 2023/11/22 11:47
 */
public interface OcrService {
     String scanPlainText(MultipartFile file);
}
