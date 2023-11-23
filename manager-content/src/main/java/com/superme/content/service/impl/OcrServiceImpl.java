package com.superme.content.service.impl;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.ocr.AipOcr;
import com.superme.common.exceptions.ParameterException;
import com.superme.common.ocr.core.OcrUtils;
import com.superme.content.service.OcrService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述: TODO
 * 作者: yanruizhi
 * 时间: 2023/11/22 11:48
 */
@Service
@Slf4j
public class OcrServiceImpl implements OcrService {

    @Resource
    private OcrUtils ocrUtils;

    @Override
    public String scanPlainText(MultipartFile file) {

        HashMap<String, String> options = new HashMap<>();

        return ocrUtils.scanPlainText(file, options);
    }
}
