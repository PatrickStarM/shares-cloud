package com.mth.content.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: mth
 * @date: 2022/9/8
 **/
@Configuration
public class LogConfig {

    @Bean
    Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }
}
