package com.superme.financial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.superme.common.beans.PageRequest;
import com.superme.common.beans.PageResponse;
import com.superme.common.util.ParameterCheckUtil;
import com.superme.financial.dao.AccountTypeDao;
import com.superme.financial.entity.AccountType;
import com.superme.financial.service.AccountTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 账户类型表(AccountType)表服务实现类
 *
 * @author makejava
 * @since 2023-11-24 17:38:33
 */
@Service
public class AccountTypeServiceImpl implements AccountTypeService {
    @Resource
    private AccountTypeDao accountTypeDao;

    @Override
    public PageResponse<AccountType> selectPage(PageRequest pageRequest) {
        ParameterCheckUtil.checkPage(pageRequest);
        LambdaQueryWrapper<AccountType> queryWrapper = new LambdaQueryWrapper<>();
        Page<AccountType> accountTypePage = accountTypeDao.selectPage(new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize()), queryWrapper);
        return new PageResponse<>(accountTypePage);
    }

    @Override
    public AccountType save(AccountType accountType) {
        ParameterCheckUtil.checkNull(accountType, "参数不能为空");
        ParameterCheckUtil.checkNull(accountType.getTypeName(), "账户类型名称不能为空");
        ParameterCheckUtil.checkNull(accountType.getTypeCode(), "账户类型code不能为空");
        accountTypeDao.insert(accountType);
        return accountTypeDao.selectById(accountType.getId());
    }

    @Override
    public AccountType updateById(AccountType accountType) {
        ParameterCheckUtil.checkNull(accountType, "参数不能为空");
        ParameterCheckUtil.checkNull(accountType.getId(), "id不能为空");
        accountTypeDao.updateById(accountType);
        return accountTypeDao.selectById(accountType.getId());
    }

    @Override
    public Boolean removeById(Long id) {
        ParameterCheckUtil.checkNull(id, "id不能为空");
        AccountType accountType = accountTypeDao.selectById(id);
        if (accountType!= null) {
            accountTypeDao.deleteById(id);
            return true;
        }
        return false;
    }
}

