package com.superme.common.ocr.core;

import com.superme.common.ocr.config.BaiduOcrConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;

/**
 * 作者: yanruizhi
 * 时间: 2023/11/22 17:06
 */
@Slf4j
public abstract class OrcUtlsAbs implements OcrUtils {

    @Resource
    private BaiduOcrConfig baiduOcrConfig;

    @PostConstruct
    public void init() {
        log.info("初始化OcrUtils");
        doInit(baiduOcrConfig);
    }

    public abstract void doInit(BaiduOcrConfig baiduOcrConfig);


    @Override
    public String scanPlainText(MultipartFile imageFile, HashMap<String, String> options) {
        return doScanPlainText(imageFile, options);
    }

    public abstract String doScanPlainText(MultipartFile imageFile, HashMap<String, String> options);
}
