package com.superme.loginservice.controller;

import com.superme.common.beans.PageRequest;
import com.superme.common.beans.PageResponse;
import com.superme.common.beans.Result;
import com.superme.loginservice.pojo.Entity.Role;
import com.superme.loginservice.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色管理前端控制器
 * 作者: yanruizhi
 * 时间: 2023/8/4 16:32
 */
@RestController
@RequestMapping("role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 分页查询角色信息
     */
    @PostMapping("getPage")
    public Result<PageResponse<Role>> getPage(PageRequest page) {
        return roleService.getPage(page);
    }

    /**
     * 新增角色信息
     */
    @PostMapping("add")
    public Result<Object> addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }

    /**
     * 删除角色信息
     *
     * @param id 角色id
     */
    @GetMapping("{id}")
    public Result<Object> delRole(@PathVariable String id) {
        return roleService.delRole(id);
    }

    /**
     * 更新角色信息
     */
    @PostMapping("update")
    public Result<Object> updateRole(@RequestBody Role role) {
        return roleService.updateRole(role);
    }
}
