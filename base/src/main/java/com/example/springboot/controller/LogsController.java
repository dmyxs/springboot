package com.example.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/logs")
@Slf4j
public class LogsController {
//    private static final Logger logger = LoggerFactory.getLogger(LogsController.class);

    @GetMapping("")
    public void logs(){
//        logger.trace("-----trace-----");
//        logger.info("-----info-----");
//        logger.error("-----error-----");
//        logger.warn("-----warn-----");
//        logger.debug("-----debug-----");
        log.trace("-----trace-----");
        log.info("-----info-----");
        log.error("-----error-----");
        log.warn("-----warn-----");
        log.debug("-----debug-----");
    }
}
