package com.superme.filemanager.enums;

import lombok.Data;

/**
 * 文件模块枚举类
 * 作者: yanruizhi
 * 时间: 2023/8/2 16:18
 */
public enum FileEnum {
    DIR_SAVADATA("savedata"),

    ;

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    FileEnum(String name) {
        this.name = name;
    }
}
