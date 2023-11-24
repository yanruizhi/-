package com.superme.financial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.superme.common.beans.PageRequest;
import com.superme.common.beans.PageResponse;
import com.superme.common.beans.Result;
import com.superme.financial.entity.Ledger;
import com.superme.financial.dao.LedgerDao;
import com.superme.financial.service.LedgerService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;

/**
 * 账本信息表(Ledger)表服务实现类
 *
 * @author makejava
 * @since 2023-11-24 17:08:06
 */
@Service("ledgerService")
public class LedgerServiceImpl implements LedgerService {
    @Resource
    private LedgerDao ledgerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Ledger queryById(Integer id) {
        return ledgerDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param ledger 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public PageResponse<Ledger> queryByPage(Ledger ledger, PageRequest pageRequest) {
        LambdaQueryWrapper<Ledger> queryWrapper = new LambdaQueryWrapper<>();
        Page<Ledger> ledgerPage = ledgerDao.selectPage(new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize()), queryWrapper);

        return new PageResponse<>(ledgerPage);
    }

    /**
     * 新增数据
     *
     * @param ledger 实例对象
     * @return 实例对象
     */
    @Override
    public Ledger insert(Ledger ledger) {
        ledgerDao.insert(ledger);
        return ledger;
    }

    /**
     * 修改数据
     *
     * @param ledger 实例对象
     * @return 实例对象
     */
    @Override
    public Ledger update(Ledger ledger) {
        if (ledger.getId() == null) {
            throw new RuntimeException("id 不能为空");
        }
        if (ledgerDao.selectById(ledger.getId()) == null) {
            throw new RuntimeException("数据不存在");
        }
        ledgerDao.updateById(ledger);

        return ledgerDao.selectById(ledger.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return ledgerDao.deleteById(id) > 0;
    }
}
