package com.superme.financial.controller;

import com.superme.common.beans.PageRequest;
import com.superme.common.beans.PageResponse;
import com.superme.common.beans.Result;
import com.superme.financial.entity.LedgerType;
import com.superme.financial.service.LedgerTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 账本类型表(LedgerType)表控制层
 *
 * @author makejava
 * @since 2023-11-27 10:21:02
 */
@RestController
@RequestMapping("ledgerType")
public class LedgerTypeController {
    /**
     * 服务对象
     */
    @Resource
    private LedgerTypeService ledgerTypeService;

    /**
     * 分页查询
     *
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @PostMapping
    public Result<PageResponse<LedgerType>> queryByPage(PageRequest pageRequest) {
        return Result.OK(ledgerTypeService.queryByPage(pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<LedgerType> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.ledgerTypeService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param ledgerType 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResponseEntity<LedgerType> add(LedgerType ledgerType) {
        return ResponseEntity.ok(this.ledgerTypeService.insert(ledgerType));
    }

    /**
     * 编辑数据
     *
     * @param ledgerType 实体
     * @return 编辑结果
     */
    @PostMapping("update")
    public ResponseEntity<LedgerType> edit(LedgerType ledgerType) {
        return ResponseEntity.ok(this.ledgerTypeService.update(ledgerType));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @PostMapping("deleteById")
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.ledgerTypeService.deleteById(id));
    }

}

