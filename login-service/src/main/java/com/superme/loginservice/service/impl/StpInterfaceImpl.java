package com.superme.loginservice.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StpInterfaceImpl implements StpInterface {
    @Override
    public List<String> getPermissionList(Object username, String loginType) {
        //真是场景区数据库查，这里就直接模拟
        List<String> list = new ArrayList<>();
        list.add("user*");
        list.add("user:update");
        list.add("user:delete");
        list.add("user:select");
        return list;
    }

    @Override
    public List<String> getRoleList(Object username, String loginType) {
        List<String> list = new ArrayList<>();
        list.add("admin");
        list.add("user");
        return list;
    }
}
