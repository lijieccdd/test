package com.jay.test.netty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.jay.test.netty")
public class TestNettyApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(TestNettyApplication.class, args);
    }
}