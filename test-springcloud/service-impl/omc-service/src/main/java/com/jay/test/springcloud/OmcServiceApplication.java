package com.jay.test.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(basePackages = { "com.jay.test.springcloud.mapper" })
public class OmcServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmcServiceApplication.class, args);
	}
}
