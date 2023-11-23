package com.superme.common.ocr.core;

import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * 描述: 文字识别工具类
 * 作者: yanruizhi
 * 时间: 2023/11/22 16:32
 */
public interface OcrUtils {
    /**
     * 扫描图片中的文字
     *
     * @param imageFile 图片文件
     * @return 文字
     */
    String scanPlainText(MultipartFile imageFile, HashMap<String, String> options);
}
