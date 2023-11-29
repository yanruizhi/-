package com.superme.financial.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 账户信息表(Account)实体类
 *
 * @author makejava
 * @since 2023-11-24 17:04:57
 */
public class Account implements Serializable {
    private static final long serialVersionUID = -68051786099246899L;
/**
     * 主键
     */
    private Integer id;
/**
     * 账户类型
     */
    private String type;
/**
     * 所属账本id
     */
    private Integer ledgerId;
/**
     * 创建时间
     */
    private Date createTime;
/**
     * 更新时间
     */
    private Date updateTime;
/**
     * 创建人id
     */
    private Long createUserId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(Integer ledgerId) {
        this.ledgerId = ledgerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

}

