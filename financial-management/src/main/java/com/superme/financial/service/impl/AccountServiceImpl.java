package com.superme.financial.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.superme.common.beans.PageRequest;
import com.superme.financial.dao.AccountDao;
import com.superme.financial.entity.Account;
import com.superme.financial.service.AccountService;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;

/**
 * 账户信息表(Account)表服务实现类
 *
 * @author makejava
 * @since 2023-11-24 17:04:58
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Account queryById(Integer id) {
        return this.accountDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param account     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Account> queryByPage(Account account, PageRequest pageRequest) {
        long total = this.accountDao.count(account);
        return new PageImpl<>(this.accountDao.queryAllByLimit(account, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    @Override
    public Account insert(Account account) {
        this.accountDao.insert(account);
        return account;
    }

    /**
     * 修改数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    @Override
    public Account update(Account account) {
        this.accountDao.update(account);
        return this.queryById(account.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.accountDao.deleteById(id) > 0;
    }
}
