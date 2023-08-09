package com.superme.common.beans;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 描述: 分页结果封装
 * 作者: yanruizhi
 * 时间: 2023/6/1 15:35
 */
@Data
public class PageResponse<T> implements Serializable {
    private List<T> list;         //要封装的list泛型集合
    private long currentPage;     //当前页码
    private long pageSize;        //每页展示的条数
    private long totalCount;     //数据总数
    private long totalPage;      //总页数

    public PageResponse() {
    }

    public PageResponse(List<T> list, int currentPage, int pageSize, long totalCount) {
        this.list = list;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    public PageResponse(PageInfo<T> page) {
        this.list = page.getList();
        this.currentPage = page.getPageNum();
        this.pageSize = page.getSize();
        this.totalCount = page.getTotal();
//        this.totalPage = (page.getTotal() % page.getSize() == 0 ? page.getTotal() / page.getSize() : page.getTotal() / page.getSize() + 1);
        this.totalPage = page.getPages();
    }
}
