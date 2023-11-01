package com.superme.filemanager.enums;

/**
 * 描述: TODO
 * 作者: yanruizhi
 * 时间: 2023/10/31 11:06
 */
public enum DirectoryEnum {
    //当前程序运行目录
    USER_DIR(System.getProperty("user.dir")),
    PHOTOS("photos"),
    ;

    private String name;

    DirectoryEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
