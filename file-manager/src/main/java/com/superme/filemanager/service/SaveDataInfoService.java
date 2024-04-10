package com.superme.filemanager.service;

import com.superme.filemanager.pojo.entity.SaveDataInfo;
import com.superme.filemanager.pojo.qo.AddSaveDataInfoQO;

/**
 * 作者: yanruizhi
 * 时间: 2024/4/10 15:42
 */
public interface SaveDataInfoService {
    SaveDataInfo add(AddSaveDataInfoQO saveDataInfo);

    SaveDataInfo delete(String id);
}
