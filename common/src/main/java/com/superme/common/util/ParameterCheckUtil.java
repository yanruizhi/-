package com.superme.common.util;


import com.superme.common.beans.PageRequest;
import com.superme.common.enums.ExceptionCodeEnum;
import com.superme.common.exceptions.ParameterException;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 描述: 参数校验工具类
 * 作者: yanruizhi
 * 时间: 2023/6/16 11:46
 */
public class ParameterCheckUtil {

    /**
     * 判断字段是否为空
     * @param t
     * @param <T>
     * @return true或false
     */
    public static <T> boolean checkNull(T t) {
        if (t instanceof List && ((List<?>) t).size() <= 0) {
            return false;
        } else {
            return t != null;
        }
    }

    /**
     * 判断字段或集合是否为空
     * @param t 字段数据
     * @param <T> 类型
     * @return true或false
     */
    public static <T> boolean isNotNull(T t) {
        if (t instanceof List && ((List<?>) t).size() <= 0) {
            return false;
        } else {
            return t != null;
        }
    }
    /**
     * 判断字段或集合是否为空
     * @param t 字段数据
     * @param <T> 类型
     * @return true或false
     */
    public static <T> boolean isNull(T t) {
        if (t instanceof List && ((List<?>) t).size() <= 0) {
            return true;
        } else {
            return t == null;
        }
    }


    /**
     * 校验字段是否为空
     * @param t
     * @param msg
     * @param <T>
     */
    public static <T> void checkNull(T t, String msg) {
        if (ObjectUtils.isEmpty(t)) {
            throw new ParameterException(msg);
        }
        if (t instanceof List &&  ((List<?>) t).size() <= 0) {
            throw new ParameterException(msg);
        }
    }

    /**
     * 校验数据库查询结果是否为空
     * @param t
     * @param <T>
     */
    public static <T> void checkNullFromBD(T t) {
        if (t == null) {
            throw new ParameterException(ExceptionCodeEnum.DATA_NULL.getCode(), ExceptionCodeEnum.DATA_NULL.getMessage());
        }
        if (t instanceof List &&  ((List<?>) t).size() <= 0) {
            throw new ParameterException(ExceptionCodeEnum.DATA_NULL_LIST.getCode(), ExceptionCodeEnum.DATA_NULL_LIST.getMessage());
        }
    }

    /**
     * 校检分页参数
     * @param page 分页信息
     */
    public static void checkPage(PageRequest page) {
        if (page == null || ObjectUtils.isEmpty(page.getPageSize()) || ObjectUtils.isEmpty(page.getCurrentPage())) {
            throw new ParameterException("分页参数异常");
        }
        if (page.getPageSize() <= 0 || page.getCurrentPage() <= 0) {
            throw new ParameterException("分页参数异常");
        }
    }
}
