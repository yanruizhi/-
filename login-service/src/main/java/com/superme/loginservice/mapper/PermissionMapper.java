package com.superme.loginservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superme.loginservice.pojo.Entity.Permission;
import org.apache.ibatis.annotations.Mapper;

/**
 * 权限查询SQL映射器
 * 作者: yanruizhi
 * 时间: 2023/8/4 16:35
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
}
