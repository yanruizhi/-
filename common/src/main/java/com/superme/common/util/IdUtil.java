package com.superme.common.util;

import java.util.UUID;

/**
 * 描述: id生产工具类
 * 作者: yanruizhi
 * 时间: 2023/10/31 10:54
 */
public class IdUtil {

    public static String getId() {
        return UUID.randomUUID().toString();
    }


}
