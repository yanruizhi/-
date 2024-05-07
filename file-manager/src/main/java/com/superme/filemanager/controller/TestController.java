package com.superme.filemanager.controller;

import com.deepoove.poi.XWPFTemplate;
import lombok.extern.slf4j.Slf4j;
import org.jodconverter.core.DocumentConverter;
import org.jodconverter.core.document.DefaultDocumentFormatRegistry;
import org.jodconverter.core.office.OfficeException;
import org.jodconverter.local.LocalConverter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/pdf")
public class TestController {

    @javax.annotation.Resource
    private DocumentConverter documentConverter;

    @PostMapping("/test")
    public void test() throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("username","xiangjiao1");
        params.put("password","******");
        params.put("age",22);
        params.put("email","专注写bug测试中文");
        Resource resource = new ClassPathResource("templates_report/test.docx");
        File file = resource.getFile();

        // 数据填充
        XWPFTemplate template = XWPFTemplate.compile(file).render(params);

        String docOutPath = System.getProperty("user.dir")+File.separator+"springboot-poi"+File.separator+"pdf"+File.separator+System.currentTimeMillis()+ ".doc";
        OutputStream outputStream = new FileOutputStream(docOutPath);
        template.write(outputStream);

        try {
            String pdfOutPath = System.getProperty("user.dir")+File.separator+"springboot-poi"+File.separator+"pdf"+File.separator+System.currentTimeMillis()+".pdf";
            documentConverter.convert(new File(docOutPath)).to(new File(pdfOutPath)).as(DefaultDocumentFormatRegistry.PDF).execute();
        } catch (OfficeException e) {
            log.error("文档转换异常:{}", e.getMessage());
        }
    }
    @PostMapping("/test2")
    public void test2(MultipartFile docxFile) throws IOException {
        try {
            String pdfOutPath = System.getProperty("user.dir") + File.separator + "springboot-poi" + File.separator + "pdf" + File.separator + docxFile.getOriginalFilename() + ".pdf";
            documentConverter.convert(docxFile.getInputStream()).to(new File(pdfOutPath)).as(DefaultDocumentFormatRegistry.PDF).execute();
        } catch (OfficeException e) {
            log.error("文档转换异常:{}", e.getMessage());
        }
    }

    @PostMapping("/test3")
    public void test3(MultipartFile docxFile, HttpServletResponse response) throws IOException {
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode(docxFile.getOriginalFilename(), "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + fileName + ".pdf");
            log.info("开始转换");
            documentConverter.convert(docxFile.getInputStream()).to(outputStream).as(DefaultDocumentFormatRegistry.PDF).execute();
            log.info("转换完成");
        } catch (OfficeException e) {
            log.error("文档转换异常:{}", e.getMessage());
        }
    }
}

