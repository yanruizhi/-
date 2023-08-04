package com.superme.loginservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superme.loginservice.pojo.Entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色查询SQL映射器
 * 作者: yanruizhi
 * 时间: 2023/8/4 16:34
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
