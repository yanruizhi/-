package com.superme.loginservice.service.impl;

import ch.qos.logback.core.LogbackException;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.superme.common.beans.Result;
import com.superme.common.exceptions.LoginException;
import com.superme.common.utils.ParameterCheckUtil;
import com.superme.loginservice.mapper.UserMapper;
import com.superme.loginservice.pojo.Entity.User;
import com.superme.loginservice.pojo.qo.LoginUser;
import com.superme.loginservice.service.LoginService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

    /**
     * 登录
     */
    @Override
    public Result<Object> login(LoginUser user) {
        ParameterCheckUtil.checkNull(user, "登录信息为空");
        ParameterCheckUtil.checkNull(user.getUsername(), "用户名不能为空");
        ParameterCheckUtil.checkNull(user.getPassword(), "密码不能为空");
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getName, user.getUsername());
        User userInfo = userMapper.selectOne(queryWrapper);
        ParameterCheckUtil.checkNull(userInfo, "用户不存在");
        if (!userInfo.getPassword().equals(user.getPassword())) {
            throw new LoginException("密码不正确");
        }
        StpUtil.login(userInfo.getId());
        return Result.OK("登录成功", userInfo);
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
}
