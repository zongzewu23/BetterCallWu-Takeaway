package com.zongzewu.bettercallwu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@SpringBootApplication
@ServletComponentScan
public class BCWApplication {
    public static void main(String[] args) {
        SpringApplication.run(BCWApplication.class,args);
        log.info("application started...");
    }
}