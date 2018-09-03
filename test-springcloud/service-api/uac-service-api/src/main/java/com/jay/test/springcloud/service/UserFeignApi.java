package com.jay.test.springcloud.service;

import com.jay.test.springcloud.model.UacUser;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/8/21 9:58
 * @Modified by :
 */
@FeignClient("uac-service")
public interface UserFeignApi {
    @RequestMapping("/selectOne")
    UacUser selectOne(@RequestParam("uacUser")UacUser uacUser);
}
