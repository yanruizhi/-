package com.superme.filemanager.service.impl;

import com.superme.common.exceptions.ParameterException;
import com.superme.common.util.ParameterCheckUtil;
import com.superme.filemanager.enums.DirectoryEnum;
import com.superme.filemanager.mapper.SaveDataInfoMapper;
import com.superme.filemanager.pojo.entity.SaveDataInfo;
import com.superme.filemanager.pojo.qo.AddSaveDataInfoQO;
import com.superme.filemanager.service.SaveDataInfoService;
import com.superme.filemanager.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 作者: yanruizhi
 * 时间: 2024/4/10 15:42
 */
@Service
@Slf4j
public class SaveDataInfoServiceImpl implements SaveDataInfoService {

    @Resource
    private SaveDataInfoMapper saveDataInfoMapper;

    @Override
    @Transactional
    public SaveDataInfo delete(String id) {
        ParameterCheckUtil.checkNull(id, "id不能为空");
        SaveDataInfo info = saveDataInfoMapper.selectById(id);
        if (info != null) {
            //删除文件
            FileUtil.delete(info.getFileUrl());
            //再删除数据
            saveDataInfoMapper.deleteById(id);
            return info;
        } else {
            throw new ParameterException("该数据不存在");
        }
    }

    @Override
    @Transactional
    public SaveDataInfo add(AddSaveDataInfoQO saveDataInfo) {
        ParameterCheckUtil.checkNull(saveDataInfo.getGameName(), "游戏名称不能为空");
        SaveDataInfo info = new SaveDataInfo();
        info.setGameName(saveDataInfo.getGameName());
        info.setDescription(saveDataInfo.getDescription());
        info.setType(saveDataInfo.getType());
        if (StringUtils.isNotBlank(saveDataInfo.getGameName())) {
            info.setSuffix(saveDataInfo.getGameName().substring(saveDataInfo.getGameName().lastIndexOf(".") + 1));
        }
        if (StringUtils.isNotBlank(saveDataInfo.getFile().getOriginalFilename())) {
            log.info("开始上传文件,文件名: {}", saveDataInfo.getFile().getOriginalFilename());
            String filePath = FileUtil.save(saveDataInfo.getFile(), DirectoryEnum.SAVE_DATA.getName());
            log.info("上传成功 {}", filePath);
            info.setFileUrl(filePath);
        }
        saveDataInfoMapper.insert(info);
        return info;
    }
}
