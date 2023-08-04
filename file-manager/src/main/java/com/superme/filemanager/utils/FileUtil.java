package com.superme.filemanager.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * FileUtil
 * 作者: yanruizhi
 * 时间: 2023/7/31 9:49
 */
public class FileUtil {



    public static String fileUtil(MultipartFile file, String path) {
        BufferedOutputStream bufferedOutputStream = null;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(
                        new File(path + file.getOriginalFilename())));
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.close();
                return file.getOriginalFilename() + "success upload";
            } catch (Exception e) {
                return file.getOriginalFilename() + "failed to upload ---> " + e;
            }
        } else {
            return file.getOriginalFilename() + "You failed to upload file was empty.";
        }
    }
}
