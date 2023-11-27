package com.superme.financial.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superme.financial.entity.LedgerType;
import org.apache.ibatis.annotations.Mapper;


/**
 * 账本类型表(LedgerType)表数据库访问层
 *
 * @author makejava
 * @since 2023-11-27 10:21:02
 */
@Mapper
public interface LedgerTypeDao extends BaseMapper<LedgerType> {

}

