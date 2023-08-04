package com.superme.loginservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.superme.common.beans.PageRequest;
import com.superme.common.beans.PageResponse;
import com.superme.common.beans.Result;
import com.superme.common.exceptions.ParameterException;
import com.superme.common.utils.ParameterCheckUtil;
import com.superme.loginservice.mapper.PermissionMapper;
import com.superme.loginservice.pojo.Entity.Permission;
import com.superme.loginservice.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * PermissionServiceImpl
 * 作者: yanruizhi
 * 时间: 2023/8/4 16:53
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;
    /**
     * 分页查询权限信息
     */
    @Override
    public Result<PageResponse<Permission>> getPage(PageRequest page) {
        ParameterCheckUtil.checkPage(page);
        return Result.OK(new PageResponse<>(permissionMapper.selectPage(new Page<>(page.getCurrentPage(), page.getPageSize()), null)));
    }
    /**
     * 新增权限信息
     */
    @Override
    public Result<Object> addPermission(Permission permission) {
        ParameterCheckUtil.checkNull(permission, "参数不能为空");
        ParameterCheckUtil.checkNull(permission.getCode(), "权限code不能为空");
        ParameterCheckUtil.checkNull(permission.getName(), "权限名称不能为空");
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Permission::getCode, permission.getCode());
        Integer integer = permissionMapper.selectCount(queryWrapper);
        if (integer > 0) {
            throw new ParameterException("权限信息已存在,请更换权限code再重新添加");
        }
        int insert = permissionMapper.insert(permission);
        return Result.OK("新增成功", insert);
    }
    /**
     * 删除权限信息
     *
     * @param id 权限id
     */
    @Override
    public Result<Object> delPermission(String id) {
        ParameterCheckUtil.checkNull(id, "id信息不能为空");
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Permission::getId, id);
        Integer integer = permissionMapper.selectCount(queryWrapper);
        if (integer == 0) {
            throw new ParameterException("没有此条数据,请刷新页面");
        }
        int delete = permissionMapper.deleteById(id);
        return Result.OK("删除成功", delete);
    }
    /**
     * 更新权限信息
     */
    @Override
    public Result<Object> updatePermission(Permission permission) {
        ParameterCheckUtil.checkNull(permission, "角色信息不能为空");
        ParameterCheckUtil.checkNull(permission.getId(), "角色id不能为空");
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Permission::getId, permission.getId());
        Integer integer = permissionMapper.selectCount(queryWrapper);
        if (integer == 0) {
            throw new ParameterException("没有此条数据,请刷新页面");
        }
        int update = permissionMapper.updateById(permission);
        return Result.OK("更新成功", update);
    }
}
