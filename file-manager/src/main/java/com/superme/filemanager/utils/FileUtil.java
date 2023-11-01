package com.superme.filemanager.utils;

import com.superme.common.exceptions.FileException;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

/**
 * FileUtil
 * 作者: yanruizhi
 * 时间: 2023/7/31 9:49
 */
public class FileUtil {



    public static String save(MultipartFile file, String path) {
        BufferedOutputStream bufferedOutputStream = null;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(path + "/" + file.getOriginalFilename()));
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.close();
                return file.getOriginalFilename() + " ---> success upload";
            } catch (Exception e) {
                if (e.getMessage().startsWith("Maximum")) {
                    throw new FileException("文件过大,单文件最大100MB");
                } else {
                    throw new FileException();
                }

            }
        } else {
            return file.getOriginalFilename() + " ---> You failed to upload file was empty.";
        }
    }
}
