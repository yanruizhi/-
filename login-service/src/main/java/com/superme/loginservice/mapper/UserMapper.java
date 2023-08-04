package com.superme.loginservice.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superme.loginservice.pojo.Entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yanruizhi
 * @since 2023-07-18
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
