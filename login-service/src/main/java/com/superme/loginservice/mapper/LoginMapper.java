package com.superme.loginservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 登录相关接口映射器
 * 作者: yanruizhi
 * 时间: 2023/8/16 17:43
 */
@Mapper
public interface LoginMapper {

    /**
     * 查询权限信息
     * @param username 用户名
     */
    List<String> getPermissionList(@Param("username") Object username);

    /**
     * 查询用户角色信息
     * @param username 用户名
     */
    List<String> getRoleList(@Param("username") Object username);
}
