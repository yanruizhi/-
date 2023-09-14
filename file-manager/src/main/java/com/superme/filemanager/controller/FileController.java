package com.superme.filemanager.controller;

import com.superme.common.beans.PageRequest;
import com.superme.common.beans.PageResponse;
import com.superme.common.beans.Result;
import com.superme.common.utils.IpUtil;
import com.superme.filemanager.pojo.Entity.FileInfo;
import com.superme.filemanager.service.FilesService;
import com.superme.filemanager.utils.FileUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 文件管理前端控制器
 * 作者: yanruizhi
 * 时间: 2023/7/31 9:42
 */
@RestController
@RequestMapping("file")
public class FileController {
    @Resource
    private FilesService fileService;

    //文件分享 TODO

    /**
     * 分享文件
     * @param fileInfoList 文件列表
     */
    @PostMapping("shareFile")
    public Result<Object> shareFile(@RequestBody List<FileInfo> fileInfoList) {
        return fileService.shareFile(fileInfoList);
    }


    @RequestMapping("/getIp")
    public String MethodName(HttpServletRequest request){
        String ip = IpUtil.getIpAddress(request);
        System.out.println("当前公网IP为: "+ip);
        return ip;
    }
    /**
     * 查询文件列表
     */
    @PostMapping("getPage")
    public Result<PageResponse<FileInfo>> getPage(PageRequest page) {
        return fileService.getPage(page);
    }
    /**
     * 单文件上传
     */
    @PostMapping("upload/single")
    public Result<Object> uploadSingle(MultipartFile file, String description) {
        return fileService.uploadSingle(file, description);
    }

    /**
     * 多文件上传
     */
    @PostMapping("upload/multi")
    public Result<Object> uploadMulti(List<MultipartFile> files, String description) {
        return fileService.uploadMulti(files, description);
    }

    /**
     * 下载文件
     */
    @GetMapping("download/{id}")
    public Result<Object> download(@PathVariable String id, HttpServletResponse response) throws IOException {
        return fileService.download(id, response);
    }

    /**
     * Java普通方式--上传文件
     *
     * @param request
     * @param name
     * @param file3
     * @param photo
     * @return
     * @throws IOException
     * @throws ServletException
     */
    @ResponseBody
    public String readFile(HttpServletRequest request, @RequestParam("name") String name, @RequestPart("file1") MultipartFile file3, @RequestPart("photo") MultipartFile photo) throws IOException, ServletException {

        File directory = new File(""); //实例化一个File对象。参数不同时，获取的最终结果也不同

        String canonicalPath = directory.getCanonicalPath();//获取标准路径。该方法需要放置在try/catch块中，或声明抛出异常

        directory.getAbsolutePath(); //获取绝对路径。

        String path = canonicalPath;
        //        String path = "I:\\spring\\spring-mybatis-plus\\src\\main\\resources\\public\\static\\";

        System.out.println(name);
        //        第一种 ： 使用 MultipartFile 封装好的 transferTo() 方法保存文件
        photo.transferTo(new File(path + photo.getOriginalFilename()));
        //
        //        第二种 ：  使用 MultipartFile 字节流保存文件
        FileUtil.save(file3, String.valueOf(path));
        //
        //		第三种 ：  使用 Part 接收文件字节流
        Part file2 = request.getPart("file2");
        file2.write(path + file2.getSubmittedFileName());

        // request.getParts() 获取的是全部参数（name,age,file1,file2），包括文件参数和非文件参数
        for (Part part : request.getParts()) {
            // 获取文件类型
            part.getContentType();
            // 获取文件大小
            part.getSize();
            // 获取文件名
            part.getSubmittedFileName();
            // 获取参数名 （name,age,file1,file2）
            part.getName();


            if (part.getContentType() != null) {
                part.write(path + part.getSubmittedFileName());
            } else {
                // 可以获取文本参数值,文本参数 part.getContentType() 为 null
                request.getParameter(part.getName());
            }
        }
        return "success";
    }

}
