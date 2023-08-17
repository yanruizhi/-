package com.superme.common.enums;

/**
 * 文件相关枚举
 * 作者: yanruizhi
 * 时间: 2023/8/7 10:44
 */
public enum FileEnum {

    DIR_BASE(System.getProperty("user.dir")),//当前程序运行目录

    ;


    private String name;

    FileEnum(String property) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
