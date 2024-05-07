package com.superme.filemanager.utils;

import com.superme.common.exceptions.FileException;
import com.superme.common.util.ParameterCheckUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * FileUtil
 * 作者: yanruizhi
 * 时间: 2023/7/31 9:49
 */
public class FileUtil {


    /**
     * 保存文件到服务器
     * @param file 要文件
     * @param DirectoryPath 文件夹
     * @return 服务器上文件地址
     */
    public static String save(MultipartFile file, String DirectoryPath) {
        BufferedOutputStream bufferedOutputStream = null;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                bufferedOutputStream = new BufferedOutputStream(Files.newOutputStream(Paths.get(DirectoryPath + "/" + file.getOriginalFilename())));
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.close();
                return file.getOriginalFilename() + " ---> success upload";
            } catch (Exception e) {
                if (e.getMessage().startsWith("Maximum")) {
                    throw new FileException("文件过大,单文件最大100MB");
                } else {
                    throw new FileException(e.getMessage());
                }

            }
        } else {
            return file.getOriginalFilename() + " ---> You failed to upload file was empty.";
        }
    }

    /**
     * 删除服务器文件
     * @param path 文件路径
     * @return 是否成功
     */
    public static boolean delete(String path) {
        ParameterCheckUtil.checkNull(path, "文件路径不能为空");
        File file = new File(path);
        return file.delete();

    }
}
