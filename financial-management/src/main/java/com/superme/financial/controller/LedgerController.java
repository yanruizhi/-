package com.superme.financial.controller;

import com.superme.common.beans.PageRequest;
import com.superme.common.beans.PageResponse;
import com.superme.common.beans.Result;
import com.superme.financial.entity.Ledger;
import com.superme.financial.service.LedgerService;


import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 账本信息表(Ledger)表控制层
 *
 * @author makejava
 * @since 2023-11-24 17:08:03
 */
@RestController
@RequestMapping("ledger")
public class LedgerController {
    /**
     * 服务对象
     */
    @Resource
    private LedgerService ledgerService;

    /**
     * 分页查询
     *
     * @param ledger 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public Result<PageResponse<Ledger>> queryByPage(Ledger ledger, PageRequest pageRequest) {
        return Result.OK(ledgerService.queryByPage(ledger, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<Ledger> queryById(@PathVariable("id") Integer id) {
        return Result.OK(ledgerService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param ledger 实体
     * @return 新增结果
     */
    @PostMapping
    public Result<Ledger> add(Ledger ledger) {
        return Result.OK(ledgerService.insert(ledger));
    }

    /**
     * 编辑数据
     *
     * @param ledger 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result<Ledger> edit(Ledger ledger) {
        return Result.OK(ledgerService.update(ledger));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public Result<Boolean> deleteById(Integer id) {
        return Result.OK(ledgerService.deleteById(id));
    }

}

