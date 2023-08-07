package com.superme.common.config;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * mybatisPlus自动生成字段控制类
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
            this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
            this.setFieldValByName("enabled", "1", metaObject);//1: 启用, 默认启用
        } catch (Exception e) {
            this.setFieldValByName("updateTime", new Date(), metaObject);
            this.setFieldValByName("createTime", new Date(), metaObject);
            this.setFieldValByName("enabled", "1", metaObject);//1: 启用, 默认启用
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        } catch (Exception e) {
            this.setFieldValByName("updateTime", new Date(), metaObject);
        }

    }

}
