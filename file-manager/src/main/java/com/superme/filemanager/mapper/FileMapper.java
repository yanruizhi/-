package com.superme.filemanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superme.filemanager.pojo.Entity.FileInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * FileMapper
 * 作者: yanruizhi
 * 时间: 2023/8/7 14:35
 */
@Mapper
public interface FileMapper extends BaseMapper<FileInfo> {
}
