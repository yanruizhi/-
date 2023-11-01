package com.superme.loginservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.superme.common.beans.PageRequest;
import com.superme.common.beans.PageResponse;
import com.superme.common.beans.Result;
import com.superme.common.exceptions.ParameterException;
import com.superme.common.util.ParameterCheckUtil;
import com.superme.loginservice.mapper.RoleMapper;
import com.superme.loginservice.pojo.Entity.Role;
import com.superme.loginservice.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * RoleServiceImpl
 * 作者: yanruizhi
 * 时间: 2023/8/4 16:51
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    /**
     * 分页查询角色信息
     */
    @Override
    public Result<PageResponse<Role>> getPage(PageRequest page) {
        ParameterCheckUtil.checkPage(page);
        Page<Role> result = roleMapper.selectPage(new Page<>(page.getCurrentPage(), page.getPageSize()), null);
        return Result.OK(new PageResponse<>(result));
    }

    /**
     * 新增角色信息
     */
    @Override
    public Result<Object> addRole(Role role) {
        ParameterCheckUtil.checkNull(role, "参数不能为空");
        ParameterCheckUtil.checkNull(role.getCode(), "角色code不能为空");
        ParameterCheckUtil.checkNull(role.getName(), "角色名称不能为空");
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getCode, role.getCode());
        Integer integer = roleMapper.selectCount(queryWrapper);
        if (integer > 0) {
            throw new ParameterException("角色信息已存在,请更换角色code再重新添加");
        }
        int insert = roleMapper.insert(role);
        return Result.OK("新增成功", insert);
    }
    /**
     * 删除角色信息
     * @param id 角色id
     */
    @Override
    public Result<Object> delRole(String id) {
        ParameterCheckUtil.checkNull(id, "id信息不能为空");
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getId, id);
        Integer integer = roleMapper.selectCount(queryWrapper);
        if (integer == 0) {
            throw new ParameterException("没有此条数据,请刷新页面");
        }
        int delete = roleMapper.deleteById(id);
        return Result.OK("删除成功", delete);
    }
    /**
     * 更新角色信息
     */
    @Override
    public Result<Object> updateRole(Role role) {
        ParameterCheckUtil.checkNull(role, "角色信息不能为空");
        ParameterCheckUtil.checkNull(role.getId(), "角色id不能为空");
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getId, role.getId());
        Integer integer = roleMapper.selectCount(queryWrapper);
        if (integer == 0) {
            throw new ParameterException("没有此条数据,请刷新页面");
        }
        int update = roleMapper.updateById(role);
        return Result.OK("更新成功", update);
    }
}
