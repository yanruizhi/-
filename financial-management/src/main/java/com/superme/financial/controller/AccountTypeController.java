package com.superme.financial.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.superme.common.beans.PageRequest;
import com.superme.common.beans.PageResponse;
import com.superme.common.beans.Result;
import com.superme.financial.entity.AccountType;
import com.superme.financial.service.AccountTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 账户类型表(AccountType)表控制层
 *
 * @author makejava
 * @since 2023-11-24 17:38:33
 */
@RestController
@RequestMapping("accountType")
public class AccountTypeController {
    /**
     * 服务对象
     */
    @Resource
    private AccountTypeService accountTypeService;

    /**
     * 分页查询所有数据
     *
     * @param pageRequest 分页对象
     * @return 所有数据
     */
    @GetMapping("getPage")
    public Result<PageResponse<AccountType>> selectAll(PageRequest pageRequest) {
        return Result.OK(accountTypeService.selectPage(pageRequest));
    }



    /**
     * 新增数据
     *
     * @param accountType 实体对象
     * @return 新增结果
     */
    @PostMapping("add")
    public Result<AccountType> insert(@RequestBody AccountType accountType) {
        return Result.OK(accountTypeService.save(accountType));
    }

    /**
     * 修改数据
     *
     * @param accountType 实体对象
     * @return 修改结果
     */
    @PostMapping("update")
    public Result<AccountType> update(@RequestBody AccountType accountType) {
        return Result.OK(accountTypeService.updateById(accountType));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @PostMapping("deleteById")
    public Result<Boolean> delete(Long id) {
        return Result.OK(accountTypeService.removeById(id));
    }
}

