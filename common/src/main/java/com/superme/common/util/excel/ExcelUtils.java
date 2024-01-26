package com.superme.common.util.excel;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 描述: 操作Excel表工具类
 * 作者: yanruizhi
 * 时间: 2023/11/29 15:58
 */
public interface ExcelUtils {
    /**
     * 导出Excel
     *
     * @param sheetName 工作表名称
     * @param datalist  数据
     * @param response  response
     * @param fileName  导出文件名
     */
    <T> void exportExcel(HttpServletResponse response, String fileName, String sheetName, List<T> datalist) throws IOException;
    <T> void exportExcel(HttpServletResponse response, String fileName, List<T> datalist) throws IOException;

    /**
     * 读取Excel信息
     * @param excelFile excel文件
     * @param sheetName 要读取的sheet名称
     * @param clazz 读取的数据实体类
     * @return 数据列表
     */
    <T> List<T> importExcel(MultipartFile excelFile, String sheetName, Class<T> clazz) throws IOException;

}


