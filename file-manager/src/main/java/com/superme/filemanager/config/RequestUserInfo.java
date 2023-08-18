package com.superme.filemanager.config;

import com.alibaba.fastjson.JSON;
import com.superme.common.enums.Constants;
import com.superme.common.exceptions.LoginException;
import com.superme.common.pojo.dto.LoginUserDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 登录用户信息
 * 作者: yanruizhi
 * 时间: 2023/8/18 10:38
 */
@Component
@Slf4j
public class RequestUserInfo {

    @Resource
    private Jedis jedis;
    @Resource
    private HttpServletRequest request;
    //获取登录用户token
    public String getToken() {
        return request.getHeader(Constants.TOKEN);
    }
    //获取登录用户完整信息
    public LoginUserDto getUserInfo() {
        String userString = jedis.get(getToken());
        if (userString == null || userString.equals("")) {
            log.info("登录信息已过期,请重新登录");
            throw new LoginException("登录信息已过期,请重新登录");
        }
        return JSON.parseObject(userString, LoginUserDto.class);
    }

    //获取登录用户角色信息
    public List<String> getUserRole() {
        LoginUserDto userInfo = getUserInfo();
        return userInfo.getRoleList();
    }
    //获取登录用户权限信息
    public List<String> getUserPermission() {
        LoginUserDto userInfo = getUserInfo();
        return userInfo.getPermissionList();
    }

    //获取用户id
    public String getUserId() {
        LoginUserDto userInfo = getUserInfo();
        return userInfo.getUserId();
    }



}
