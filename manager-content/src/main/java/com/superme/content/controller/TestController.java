package com.superme.content.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.superme.common.util.excel.ExcelUtils;
import com.superme.content.entity.Dog;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述: TODO
 * 作者: yanruizhi
 * 时间: 2023/11/27 15:22
 */
@RestController
@RequestMapping("test")
@Slf4j
public class TestController {

    @Resource
    private ExcelUtils excelUtils;

    @XxlJob(value = "test")
    public void test() {
        log.info("-----------测试------------");

    }

    @RequestMapping("log")
    public void log(String msg) {
        log.info(msg);
        log.error(msg);
        log.warn(msg);
        log.debug(msg);
        log.trace(msg);

    }

    @PostMapping("excel/read")
    public void readExcel(MultipartFile excelFile) throws IOException {
        ExcelReader reader1 = ExcelUtil.getReader(excelFile.getInputStream());
        List<List<Object>> read = reader1.read();

        List<Dog> dogs = excelUtils.importExcel(excelFile, null, Dog.class);
        for (Dog dog : dogs) {
            System.out.println(dog);
        }
        System.out.println("Ok");
    }

    @PostMapping("excel/write")
    public void writeExcel(HttpServletResponse response) throws IOException {

        List<Dog> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Dog dog = new Dog("大黄-" + i, "黄色");

            list.add(dog);
        }
        excelUtils.exportExcel(response, "测试", "测试", list);

//        // 通过工具类创建writer，默认创建xls格式
//        ExcelWriter writer = ExcelUtil.getWriter();
//        // 一次性写出内容，使用默认样式，强制输出标题
//        writer.write(list, true);
//
//        //response为HttpServletResponse对象
//        response.setContentType("application/vnd.ms-excel;charset=utf-8");
//        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
//        response.setHeader("Content-Disposition", "attachment;filename=test.xlsx");
//        //out为OutputStream，需要写出到的目标流
//        ServletOutputStream out = response.getOutputStream();
//
//        writer.flush(out, true);
//        // 关闭writer，释放内存
//        writer.close();
//        //此处记得关闭输出Servlet流
//        IoUtil.close(out);


    }

}
