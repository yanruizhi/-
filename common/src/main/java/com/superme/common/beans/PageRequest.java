package com.superme.common.beans;

import lombok.Data;

/**
 * 分页信息
 * 作者: yanruizhi
 * 时间: 2023/8/4 16:39
 */
@Data
public class PageRequest {
    private Integer currentPage = 1;
    private Integer pageSize = 10;

}
