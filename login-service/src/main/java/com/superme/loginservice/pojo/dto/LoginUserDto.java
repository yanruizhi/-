package com.superme.loginservice.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 登录用户基本信息
 * 作者: yanruizhi
 * 时间: 2023/8/17 10:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDto {
    private String username;//用户名
    private String email;//邮箱
    private String phone;//手机号
    private String token;//登录token
    List<String> permissionList;//权限列表
    List<String> roleList;//角色列表
}
