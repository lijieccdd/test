package com.jay.test.springcloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/5/26 17:20
 * @Modified by :
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = { "com.jay.test.springcloud.mapper" })
public class UserServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(
                UserServiceApplication.class)
                .web(true).run(args);
    }
}
