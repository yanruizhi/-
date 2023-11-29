package com.superme.financial.service;

import com.superme.common.beans.PageRequest;
import com.superme.common.beans.PageResponse;
import com.superme.financial.entity.LedgerType;


/**
 * 账本类型表(LedgerType)表服务接口
 *
 * @author makejava
 * @since 2023-11-27 10:21:02
 */
public interface LedgerTypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    LedgerType queryById(Integer id);

    /**
     * 分页查询
     *
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    PageResponse<LedgerType> queryByPage(PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param ledgerType 实例对象
     * @return 实例对象
     */
    LedgerType insert(LedgerType ledgerType);

    /**
     * 修改数据
     *
     * @param ledgerType 实例对象
     * @return 实例对象
     */
    LedgerType update(LedgerType ledgerType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
