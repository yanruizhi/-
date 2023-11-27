package com.superme.financial.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superme.financial.entity.Ledger;
import org.apache.ibatis.annotations.Mapper;


/**
 * 账本信息表(Ledger)表数据库访问层
 *
 * @author makejava
 * @since 2023-11-24 17:08:03
 */
@Mapper
public interface LedgerDao extends BaseMapper<Ledger> {


}

