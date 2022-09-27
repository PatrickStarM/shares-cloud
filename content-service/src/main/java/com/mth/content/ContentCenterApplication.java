package com.mth.content;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description:
 * @author: mth
 * @date: 2022/9/6
 **/
@SpringBootApplication
@EnableFeignClients(clients = {com.mth.content.openfeign.UserService.class})
public class ContentCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentCenterApplication.class);
    }
}
