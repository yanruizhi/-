package com.superme.common.util.excel.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.util.StringUtil;
import com.superme.common.exceptions.ParameterException;
import com.superme.common.util.excel.ExcelUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 描述: 操作Excel表工具类
 * 作者: yanruizhi
 * 时间: 2023/11/29 16:03
 */

@Component
public class ExcelUtilsImpl implements ExcelUtils {
    /**
     * 导出Excel
     * @param response
     * @param fileName 文件名
     * @param sheetName 表格名
     * @param datalist 数据
     * @param <T> 数据类型
     */
    private <T> void doExportExcel(HttpServletResponse response, String fileName, String sheetName, List<T> datalist) throws IOException {
        if (StringUtil.isEmpty(fileName)) {
            throw new ParameterException("文件名不能为空");
        }
        if (StringUtil.isEmpty(sheetName)) {
            sheetName = "sheet1";
        }
        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.renameSheet(sheetName).write(datalist, true);

        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        fileName = URLEncoder.encode(fileName, "UTF-8");//.replaceAll("\\+", "%20");
        response.setHeader("Content-Type", "application/xlsx");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        //out为OutputStream，需要写出到的目标流
        ServletOutputStream out = response.getOutputStream();

        writer.flush(out, true);
        // 关闭writer，释放内存
        writer.close();
        //此处记得关闭输出Servlet流
        IoUtil.close(out);
    }
    @Override
    public <T> void exportExcel(HttpServletResponse response, String fileName, String sheetName, List<T> datalist) throws IOException {
        doExportExcel(response, fileName, sheetName, datalist);
    }

    @Override
    public <T> void exportExcel(HttpServletResponse response, String fileName, List<T> datalist) throws IOException {
            doExportExcel(response, fileName, "sheet1", datalist);
    }

    @Override
    public <T> List<T> importExcel(MultipartFile excelFile, String sheetName, Class<T> clazz) throws IOException {
        if (excelFile == null) {
            throw new ParameterException("文件不能为空");
        }
        ExcelReader reader = ExcelUtil.getReader(excelFile.getInputStream());
        //标题行 1 , 从第一条数据开始读
        List<T> list = reader.read(1, 0, clazz);
        // 关闭reader，释放内存
        reader.close();
        return list;
    }
}
