package com.superme.financial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.superme.common.beans.PageRequest;
import com.superme.common.beans.PageResponse;
import com.superme.common.util.ParameterCheckUtil;
import com.superme.financial.entity.LedgerType;
import com.superme.financial.dao.LedgerTypeDao;
import com.superme.financial.service.LedgerTypeService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;

/**
 * 账本类型表(LedgerType)表服务实现类
 *
 * @author makejava
 * @since 2023-11-27 10:21:02
 */
@Service("ledgerTypeService")
public class LedgerTypeServiceImpl implements LedgerTypeService {
    @Resource
    private LedgerTypeDao ledgerTypeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public LedgerType queryById(Integer id) {
        ParameterCheckUtil.checkNull(id, "id不能为空");
        return ledgerTypeDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public PageResponse<LedgerType> queryByPage(PageRequest pageRequest) {
        ParameterCheckUtil.checkPage(pageRequest);
        LambdaQueryWrapper<LedgerType> queryWrapper = new LambdaQueryWrapper<>();
        Page<LedgerType> page = ledgerTypeDao.selectPage(new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize()), queryWrapper);
        return new PageResponse<>(page);
    }

    /**
     * 新增数据
     *
     * @param ledgerType 实例对象
     * @return 实例对象
     */
    @Override
    public LedgerType insert(LedgerType ledgerType) {
        this.ledgerTypeDao.insert(ledgerType);
        return ledgerType;
    }

    /**
     * 修改数据
     *
     * @param ledgerType 实例对象
     * @return 实例对象
     */
    @Override
    public LedgerType update(LedgerType ledgerType) {
        ParameterCheckUtil.checkNull(ledgerType.getId(), "id不能为空");
        ledgerTypeDao.updateById(ledgerType);
        return queryById(ledgerType.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return ledgerTypeDao.deleteById(id) > 0;
    }
}
