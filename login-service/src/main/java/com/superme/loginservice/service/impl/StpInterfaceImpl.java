package com.superme.loginservice.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import com.superme.loginservice.mapper.LoginMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private LoginMapper loginMapper;

    /**
     * 获取用户权限列表
     * @param username 用户id或用户名
     * @param loginType 登录类型
     */
    @Override
    public List<String> getPermissionList(Object username, String loginType) {
        //不需要校验参数,框架已经校验
        return loginMapper.getPermissionList(username);
    }
    /**
     * 获取用户角色列表
     * @param username 用户id或用户名
     * @param loginType 登录类型
     */
    @Override
    public List<String> getRoleList(Object username, String loginType) {
        return loginMapper.getRoleList(username);
    }

}
