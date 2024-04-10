package com.superme.filemanager.controller;

import com.superme.filemanager.pojo.entity.SaveDataInfo;
import com.superme.filemanager.pojo.qo.AddSaveDataInfoQO;
import com.superme.filemanager.service.SaveDataInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 描述: 存档文件信息
 * 作者: yanruizhi
 * 时间: 2024/4/10 15:31
 */
@RestController
@RequestMapping("saveData")
public class SaveDataInfoController {

    @Resource
    private SaveDataInfoService saveDataInfoService;

    @PostMapping("add")
    public SaveDataInfo add(AddSaveDataInfoQO saveDataInfo) {
        return saveDataInfoService.add(saveDataInfo);
    }

    @PostMapping("delete")
    public SaveDataInfo delete(String id) {
        return saveDataInfoService.delete(id);
    }


}
