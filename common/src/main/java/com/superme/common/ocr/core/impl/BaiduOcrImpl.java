package com.superme.common.ocr.core.impl;


import com.alibaba.fastjson.JSON;
import com.baidu.aip.ocr.AipOcr;
import com.superme.common.exceptions.ParameterException;
import com.superme.common.ocr.config.BaiduOcrConfig;
import com.superme.common.ocr.core.OrcUtlsAbs;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: yanruizhi
 * 时间: 2023/11/22 17:14
 */
@Component
@Slf4j
public class BaiduOcrImpl extends OrcUtlsAbs {

    private AipOcr aipOcr;
    @Override
    public void doInit(BaiduOcrConfig baiduOcrConfig) {
        if (StringUtils.isEmpty(baiduOcrConfig.getApiKey()) || StringUtils.isEmpty(baiduOcrConfig.getSecretKey()) || StringUtils.isEmpty(baiduOcrConfig.getAppId())) {
            log.error("请配置百度ocr相关配置");
        } else {
            aipOcr = new AipOcr(baiduOcrConfig.getAppId(), baiduOcrConfig.getApiKey(), baiduOcrConfig.getSecretKey());
            log.info("百度ocr初始化成功");
        }
    }

    @Override
    public String doScanPlainText(MultipartFile imageFile, HashMap<String, String> options) {
        StringBuilder result = new StringBuilder();
        //1.参数校验
        if (ObjectUtils.isEmpty(imageFile)) {
            log.info("扫描普通文本: 图片文件不能为空");
            throw new ParameterException("扫描普通文本: 图片文件不能为空");
        }
        //2.开始扫描
        JSONObject jsonObject = null;
        if (options == null) {
            options = new HashMap<>();
        }
        try {
            jsonObject = aipOcr.basicAccurateGeneral(imageFile.getBytes(), options);
            //            jsonObject = ocrUtils.basicAccurateGeneral(file.getBytes(), options);
        } catch (IOException e) {
            log.info("扫描普通文本: 文本识别异常");
            log.error("errorInfo:{}", e.getMessage());
            throw new ParameterException("扫描普通文本: 文本识别异常");
        }
        //3.扫描结果
        Map map = JSON.parseObject(jsonObject.toString(), Map.class);
        List<Map> words_result = (List<Map>) map.get("words_result");
        //4.拼接字符串
        for (Map m : words_result) {
            result.append(m.get("words")).append(" ");
        }
        //5.去除两端空格并返回
        return result.toString().trim();
    }
}
