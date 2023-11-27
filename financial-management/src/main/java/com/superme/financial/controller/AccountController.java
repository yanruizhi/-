package com.superme.financial.controller;

import com.superme.common.beans.PageRequest;
import com.superme.common.beans.PageResponse;
import com.superme.common.beans.Result;
import com.superme.financial.entity.Account;
import com.superme.financial.service.AccountService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 账户信息表(Account)表控制层
 *
 * @author makejava
 * @since 2023-11-24 17:04:55
 */
@RestController
@RequestMapping("account")
public class AccountController {
    /**
     * 服务对象
     */
    @Resource
    private AccountService accountService;

    /**
     * 分页查询
     *
     * @param account 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @PostMapping("getPage")
    public Result<PageResponse<Account>> queryByPage(Account account, PageRequest pageRequest) {
        return Result.OK(accountService.queryByPage(account, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<Account> queryById(@PathVariable("id") Integer id) {
        return Result.OK(accountService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param account 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public Result<Account> add(Account account) {
        return Result.OK(accountService.insert(account));
    }

    /**
     * 编辑数据
     *
     * @param account 实体
     * @return 编辑结果
     */
    @PostMapping("update")
    public Result<Account> edit(Account account) {
        return Result.OK(accountService.update(account));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @PostMapping("deleteById")
    public Result<Boolean> deleteById(String id) {
        return Result.OK(accountService.deleteById(id));
    }

}

