package com.superme.financial.service.impl;

import com.superme.financial.entity.Ledger;
import com.superme.financial.dao.LedgerDao;
import com.superme.financial.service.LedgerService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

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
        return this.ledgerDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param ledger 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Ledger> queryByPage(Ledger ledger, PageRequest pageRequest) {
        long total = this.ledgerDao.count(ledger);
        return new PageImpl<>(this.ledgerDao.queryAllByLimit(ledger, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param ledger 实例对象
     * @return 实例对象
     */
    @Override
    public Ledger insert(Ledger ledger) {
        this.ledgerDao.insert(ledger);
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
        this.ledgerDao.update(ledger);
        return this.queryById(ledger.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.ledgerDao.deleteById(id) > 0;
    }
}
