package com.superme.content.controller;

import com.superme.content.service.OcrService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 描述: 文字识别Controller
 * 作者: yanruizhi
 * 时间: 2023/11/22 11:48
 */
@RestController
@RequestMapping("ocr")
public class OcrController {

    @Resource
    private OcrService ocrService;

    @RequestMapping("/scanPlainText")
    public String scanPlainText(MultipartFile file) {
        return ocrService.scanPlainText(file);
    }
}
