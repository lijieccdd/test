package com.jay.test.springcloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/5/26 17:20
 * @Modified by :
 */
@EnableDiscoveryClient
@SpringBootApplication
public class UserServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(
                UserServiceApplication.class)
                .web(true).run(args);
    }
}
