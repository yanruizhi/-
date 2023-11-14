package com.superme.filemanager.service.impl;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.superme.common.constant.Constant;
import com.superme.common.exceptions.ParameterException;
import com.superme.common.util.DateUtil;
import com.superme.common.util.ParameterCheckUtil;
import com.superme.filemanager.enums.DirectoryEnum;
import com.superme.filemanager.mapper.MediaMapper;
import com.superme.filemanager.pojo.Entity.TMedia;
import com.superme.filemanager.service.MediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 描述: 照片服务实现类
 * 作者: yanruizhi
 * 时间: 2023/10/31 10:08
 */
@Slf4j
@Service
public class MediaServiceImpl implements MediaService {
    @Resource
    private MediaMapper mediaMapper;

    /**
     * 保存媒资信息
     *
     * @param files 文件列表
     */
    @Override
    public Integer saveMedia(List<MultipartFile> files) {
        ParameterCheckUtil.checkNull(files, "文件列表不能为空");
        TMedia tMedia;
        for (MultipartFile multipartFile : files) {
            tMedia = new TMedia();
            File file = null;
            Metadata metadata = null;
            String suffix = null;
            File directory = new File(DirectoryEnum.USER_DIR.getName() + "\\" + DirectoryEnum.PHOTOS.getName());
            try {
                String originalFilename = multipartFile.getOriginalFilename();
                String[] filename = new String[0];
                if (originalFilename != null) {
                    filename = originalFilename.split("\\.");
                }
                suffix = filename[1];
                tMedia.setMediaName(filename[0]);//名称
                if (filename[1] != null) {
                    tMedia.setMediaSuffix(suffix);//后缀
                    if (Arrays.asList(Constant.PICTURE_TYPE).contains(suffix)) {
                        tMedia.setMediaType("1");
                    } else if (Arrays.asList(Constant.VIDEO_TYPE).contains(suffix)) {
                        tMedia.setMediaType("2");
                    } else if (Arrays.asList(Constant.AUDIO_TYPE).contains(suffix)) {
                        tMedia.setMediaType("3");
                    } else {
                        tMedia.setMediaType("4");
                    }
                }
                file = File.createTempFile(filename[0], "."+filename[1], directory);
                multipartFile.transferTo(file);
                //jvm退出时删除零时文件
                file.deleteOnExit();
            } catch (IOException e) {
                log.info("生成媒资文件异常");
            }
            //读取媒资信息
            try {
                if (file != null) {
                    metadata = ImageMetadataReader.readMetadata(file);
                }
            } catch (ImageProcessingException | IOException e) {
                log.info("读取媒资信息异常");
            }
            if (metadata == null) {
                throw new ParameterException("系统异常");
            }
            //遍历信息,并获取
            for (Directory d : metadata.getDirectories()) {
                for (Tag tag : d.getTags()) {
                    String tagName = tag.getTagName();  //标签名
                    String desc = tag.getDescription(); //标签信息
                    System.out.println(tagName + "===" + desc);//照片信息
                    if (suffix != null) {
                        if (Arrays.asList(Constant.PICTURE_TYPE).contains(suffix)) {
                            //处理图片
                            switch (tagName) {
                                case "Date/Time Original" :
                                    tMedia.setShootingTime(DateUtil.getLocalDateTime(desc));
                                    break;
                                case "GPS Latitude":
                                    tMedia.setLatitude(desc);//经度
                                    break;
                                case "GPS Longitude":
                                    tMedia.setLongitude(desc);//纬度
                                    break;
                                case "GPS Altitude":
                                    tMedia.setAltitude(desc.split(" ")[0]);//海拔
                                    break;
                                case "File Size":
                                    tMedia.setSize(Long.valueOf(desc.split(" ")[0]));//文件大小
                                    break;
                                default:
                                    break;

                            }
                        } else if (Arrays.asList(Constant.VIDEO_TYPE).contains(suffix)) {
                            //处理视频
                            switch (tagName) {
                                case "Creation Time" :
                                    //有两个拍摄时间,这里取一个
                                    if (tMedia.getShootingTime() == null) {
                                        tMedia.setShootingTime(DateUtil.getLocalDateTimeByCST(desc));//拍摄时间
                                    }
                                    break;
                                case "File Size":
                                    tMedia.setSize(Long.valueOf(desc.split(" ")[0]));//文件大小
                                    break;
                                case "Duration in Seconds":
                                    tMedia.setDurationTime(desc);
                                    break;
                                default:
                                    break;

                            }
                        } else if (Arrays.asList(Constant.AUDIO_TYPE).contains(suffix)) {
                            //处理音频
                            System.out.println("处理音频");
                        }
                    }

                }
            }
            mediaMapper.insert(tMedia);
        }
        return 1;
    }
}
