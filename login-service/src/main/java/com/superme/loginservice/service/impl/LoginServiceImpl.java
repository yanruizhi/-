package com.superme.loginservice.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.superme.common.beans.Result;
import com.superme.common.enums.CommonEnum;
import com.superme.common.enums.Constants;
import com.superme.common.exceptions.LoginException;
import com.superme.common.exceptions.ParameterException;
import com.superme.common.utils.ParameterCheckUtil;
import com.superme.loginservice.mapper.UserMapper;
import com.superme.loginservice.pojo.Entity.User;
import com.superme.loginservice.pojo.dto.LoginUserDto;
import com.superme.loginservice.pojo.qo.LoginUserQo;
import com.superme.loginservice.service.LoginService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * 描述: TODO
 * 作者: yanruizhi
 * 时间: 2023/7/18 16:44
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private DefaultKaptcha defaultKaptcha;
    @Resource
    private Jedis jedis;
    @Resource
    private RedisTemplate redisTemplate;


    /**
     * 登录
     */
    @Override
    public Result<Object> login(LoginUserQo user) {
        //参数校验
        ParameterCheckUtil.checkNull(user, "登录信息为空");
        ParameterCheckUtil.checkNull(user.getUsername(), "用户名不能为空");
        ParameterCheckUtil.checkNull(user.getPassword(), "密码不能为空");
        ParameterCheckUtil.checkNull(user.getVcode(), "验证码不能为空");
        //校验验证码
        Boolean exists = jedis.exists(user.getVcode());
        if (!exists) {
            throw new LoginException("验证码错误");
        }
        //查询登录用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getName, user.getUsername());
        User userInfo = userMapper.selectOne(queryWrapper);
        ParameterCheckUtil.checkNull(userInfo, "用户不存在");
        if (!userInfo.getPassword().equals(user.getPassword())) {
            throw new LoginException("密码不正确");
        }
        StpUtil.login(userInfo.getId());
        List<String> permissionList = StpUtil.getPermissionList(user.getUsername());//用户权限
        List<String> roleList = StpUtil.getRoleList(user.getUsername());//用户角色
        permissionList.removeAll(Collections.singleton(null));
        roleList.removeAll(Collections.singleton(null));
        String tokenValue = StpUtil.getTokenValue();
        LoginUserDto loginUserDto = new LoginUserDto(String.valueOf(userInfo.getId()), userInfo.getName(), userInfo.getEmail(), userInfo.getPhone(), tokenValue, permissionList, roleList);
        //user信息存入redis缓存,过期时间1天
        jedis.setex(tokenValue, Constants.TIME_ONE_DAY, JSON.toJSONString(loginUserDto));
        return Result.OK("登录成功", loginUserDto);
    }

    @Override
    public Long getUserIdByUsername(String username) {
        ParameterCheckUtil.checkNull(username, "用户名不能为空");
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getName, username);
        return userMapper.selectOne(queryWrapper).getId();
    }

    /**
     * 生成验证码
     */
    @Override
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        //生成验证码字符串
        String code = defaultKaptcha.createText();
        jedis.setex(code, 300, code);
        //将验证码保存到session中
        request.getSession().setAttribute("verifyCode", code);

        //生成验证码图片
        BufferedImage image = defaultKaptcha.createImage(code);
        //设置响应头
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(image, "jpg", outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new LoginException("生成验证码异常");
        }

    }
    /**
     * 登出
     */
    @Override
    public Result<Object> logout(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("token");
        if (ObjectUtils.isEmpty(token)) {
            throw new LoginException("token信息不存在");
        }
        Boolean exists = jedis.exists(token);
        Long flag = 0L;
        if (exists) {
            flag = jedis.del(token);
        }
        if (flag > 0) {
            return Result.OK("登出成功");
        } else {
            throw new LoginException("登出异常");
        }

    }
}
