package com.superme.financial.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 账户类型表(AccountType)表实体类
 *
 * @author makejava
 * @since 2023-11-24 17:38:33
 */

@Data
public class AccountType {
    //主键
    @TableId(type = IdType.AUTO)
    private Integer id;

    //类型code
    private String typeCode;
    //账户类型名称
    private String typeName;


}

