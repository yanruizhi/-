package com.superme.financial.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superme.financial.entity.AccountType;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账户类型表(AccountType)表数据库访问层
 *
 * @author makejava
 * @since 2023-11-24 17:38:33
 */
@Mapper
public interface AccountTypeDao extends BaseMapper<AccountType> {

}

