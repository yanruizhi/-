package com.superme.financial.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superme.financial.entity.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账户信息表(Account)表数据库访问层
 *
 * @author makejava
 * @since 2023-11-24 17:04:57
 */
@Mapper
public interface AccountDao extends BaseMapper<Account> {

}

