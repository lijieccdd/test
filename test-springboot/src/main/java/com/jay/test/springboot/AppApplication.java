package com.jay.test.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
@ComponentScan(value = "com.jay.test.springboot")
public class AppApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppApplication.class, args);
    }
}