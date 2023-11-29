package com.superme.financial.service;

import com.superme.common.beans.PageRequest;
import com.superme.common.beans.PageResponse;
import com.superme.financial.entity.AccountType;

/**
 * 账户类型表(AccountType)表服务接口
 *
 * @author makejava
 * @since 2023-11-24 17:38:33
 */
public interface AccountTypeService {

    PageResponse<AccountType> selectPage(PageRequest pageRequest);

    AccountType save(AccountType accountType);

    AccountType updateById(AccountType accountType);

    Boolean removeById(Long id);
}

