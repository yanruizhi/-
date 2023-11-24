package com.superme.financial.dao;

import com.superme.financial.entity.Ledger;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 账本信息表(Ledger)表数据库访问层
 *
 * @author makejava
 * @since 2023-11-24 17:08:03
 */
public interface LedgerDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Ledger queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param ledger 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Ledger> queryAllByLimit(Ledger ledger, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param ledger 查询条件
     * @return 总行数
     */
    long count(Ledger ledger);

    /**
     * 新增数据
     *
     * @param ledger 实例对象
     * @return 影响行数
     */
    int insert(Ledger ledger);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Ledger> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Ledger> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Ledger> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Ledger> entities);

    /**
     * 修改数据
     *
     * @param ledger 实例对象
     * @return 影响行数
     */
    int update(Ledger ledger);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

