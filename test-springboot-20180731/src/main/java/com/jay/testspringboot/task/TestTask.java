package com.jay.testspringboot.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/7/31 16:23
 * @Modified by :
 */
@Component
public class TestTask {
    @Scheduled(cron="0 0 * * * ?")
    public void print(){
        System.out.println("excute task:"+new Date());
    }
}
