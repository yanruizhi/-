package com.superme.financial.entity;

import java.io.Serializable;

/**
 * 账本类型表(LedgerType)实体类
 *
 * @author makejava
 * @since 2023-11-27 10:21:02
 */
public class LedgerType implements Serializable {
    private static final long serialVersionUID = -57290463017120806L;
/**
     * 主键
     */
    private Integer id;
/**
     * 类型code
     */
    private String typecode;
/**
     * 类型名称
     */
    private String typename;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypecode() {
        return typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

}

