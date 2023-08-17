package com.superme.common.enums;

/**
 * 描述: TODO
 * 作者: yanruizhi
 * 时间: 2023/8/17 10:51
 */
public enum CommonEnum {
    DAY_SECONDS("86400");

    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    CommonEnum(String date) {

    }
}
