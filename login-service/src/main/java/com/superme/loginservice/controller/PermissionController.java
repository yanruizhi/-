package com.superme.loginservice.controller;

import com.superme.common.beans.PageRequest;
import com.superme.common.beans.PageResponse;
import com.superme.common.beans.Result;
import com.superme.loginservice.pojo.Entity.Permission;
import com.superme.loginservice.pojo.Entity.Role;
import com.superme.loginservice.service.PermissionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 权限管理前端控制器
 * 作者: yanruizhi
 * 时间: 2023/8/4 17:20
 */
@RequestMapping("permission")
@RestController
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    /**
     * 分页查询权限信息
     */
    @PostMapping("getPage")
    public Result<PageResponse<Permission>> getPage(PageRequest page) {
        return permissionService.getPage(page);
    }


    /**
     * 新增权限信息
     */
    @PostMapping("add")
    public Result<Object> addPermission(@RequestBody Permission permission) {
        return permissionService.addPermission(permission);
    }

    /**
     * 删除权限信息
     *
     * @param id 权限id
     */
    @GetMapping("{id}")
    public Result<Object> delPermission(@PathVariable String id) {
        return permissionService.delPermission(id);
    }

    /**
     * 更新权限信息
     */
    @PostMapping("update")
    public Result<Object> updatePermission(@RequestBody Permission permission) {
        return permissionService.updatePermission(permission);
    }
}
