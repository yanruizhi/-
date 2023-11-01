package com.superme.common.util;


import com.drew.metadata.Metadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 描述: 文件工具类
 * 作者: yanruizhi
 * 时间: 2023/10/31 10:23
 */
public class FileUtil {

    public static File multipartFileToFile(MultipartFile multipartFile) {
        File file = null;
        Metadata metadata = null;

        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String[] filename = originalFilename.split("\\.");
            file = File.createTempFile(filename[0], filename[1]);
            multipartFile.transferTo(file);
            //jvm退出时删除零时文件
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 将MultipartFile转换为File
     *
     *
     * @param outFilePath 参数
     * @param multiFile 参数
     * @return 执行结果
     */
    public static File multipartFileToFile(String outFilePath, MultipartFile multiFile) {
        // 获取文件名
        if (null == multiFile) {
            return null;
        }
        String fileName = multiFile.getOriginalFilename();
        if (null == fileName) {
            return null;
        }
        try {
            File toFile;
            InputStream ins;
            ins = multiFile.getInputStream();
            //指定存储路径
            toFile = new File(outFilePath.concat(File.separator).concat(multiFile.getOriginalFilename()));
            inputStreamToFile(ins, toFile);
            return toFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private static void inputStreamToFile(InputStream ins, File file) {
        try (OutputStream os = new FileOutputStream(file)) {
            int bytesRead;
            int bytes = 8192;
            byte[] buffer = new byte[bytes];
            while ((bytesRead = ins.read(buffer, 0, bytes)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
