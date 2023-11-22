package com.superme.content.service.impl;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.ocr.AipOcr;
import com.superme.common.exceptions.ParameterException;
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
    private AipOcr aipOcr;

    @Override
    public String scanPlainText(MultipartFile file) {
        StringBuilder result = new StringBuilder();
        //1.参数校验
        if (ObjectUtils.isEmpty(file)) {
            log.info("扫描普通文本: 图片文件不能为空");
            throw new ParameterException("扫描普通文本: 图片文件不能为空");
        }
        //2.开始扫描
        JSONObject jsonObject = null;
        HashMap<String, String> options = new HashMap<>();
        try {
            jsonObject = aipOcr.basicAccurateGeneral(file.getBytes(), options);
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
