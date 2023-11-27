package com.superme.content.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述: TODO
 * 作者: yanruizhi
 * 时间: 2023/11/27 15:22
 */
@RestController
@RequestMapping("test")
@Slf4j
public class TestController {

    @RequestMapping("log")
    public void log(String msg) {
        log.info(msg);
        log.error(msg);
        log.warn(msg);
        log.debug(msg);
        log.trace(msg);

    }
}
