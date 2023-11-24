package com.superme.financial.controller;

import com.superme.financial.entity.Ledger;
import com.superme.financial.service.LedgerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Page<Ledger>> queryByPage(Ledger ledger, PageRequest pageRequest) {
        return ResponseEntity.ok(this.ledgerService.queryByPage(ledger, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Ledger> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.ledgerService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param ledger 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Ledger> add(Ledger ledger) {
        return ResponseEntity.ok(this.ledgerService.insert(ledger));
    }

    /**
     * 编辑数据
     *
     * @param ledger 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Ledger> edit(Ledger ledger) {
        return ResponseEntity.ok(this.ledgerService.update(ledger));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.ledgerService.deleteById(id));
    }

}

