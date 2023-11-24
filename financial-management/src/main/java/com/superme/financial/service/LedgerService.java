package com.superme.financial.service;

import com.superme.common.beans.PageRequest;
import com.superme.common.beans.PageResponse;
import com.superme.common.beans.Result;
import com.superme.financial.entity.Ledger;


/**
 * 账本信息表(Ledger)表服务接口
 *
 * @author makejava
 * @since 2023-11-24 17:08:06
 */
public interface LedgerService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Ledger queryById(Integer id);

    /**
     * 分页查询
     *
     * @param ledger 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    PageResponse<Ledger> queryByPage(Ledger ledger, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param ledger 实例对象
     * @return 实例对象
     */
    Ledger insert(Ledger ledger);

    /**
     * 修改数据
     *
     * @param ledger 实例对象
     * @return 实例对象
     */
    Ledger update(Ledger ledger);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
