package com.superme.loginservice.service;

import com.superme.common.beans.PageRequest;
import com.superme.common.beans.PageResponse;
import com.superme.common.beans.Result;
import com.superme.loginservice.pojo.Entity.Permission;

/**
 * PermissionService
 * 作者: yanruizhi
 * 时间: 2023/8/4 16:52
 */
public interface PermissionService {
    /**
     * 分页查询权限信息
     */
    Result<PageResponse<Permission>> getPage(PageRequest page);

    /**
     * 新增权限信息
     */
    Result<Object> addPermission(Permission permission);
    /**
     * 删除权限信息
     *
     * @param id 权限id
     */
    Result<Object> delPermission(String id);
    /**
     * 更新权限信息
     */
    Result<Object> updatePermission(Permission permission);
}
