package com.superme.loginservice.service;

import com.superme.common.beans.PageRequest;
import com.superme.common.beans.PageResponse;
import com.superme.common.beans.Result;
import com.superme.loginservice.pojo.Entity.Role;

/**
 * RoleService
 * 作者: yanruizhi
 * 时间: 2023/8/4 16:51
 */
public interface RoleService {
    /**
     * 分页查询角色信息
     */
    Result<PageResponse<Role>> getPage(PageRequest page);
    /**
     * 新增角色信息
     */
    Result<Object> addRole(Role role);
    /**
     * 删除角色信息
     * @param id 角色id
     */
    Result<Object> delRole(String id);
    /**
     * 更新角色信息
     */
    Result<Object> updateRole(Role role);
}
