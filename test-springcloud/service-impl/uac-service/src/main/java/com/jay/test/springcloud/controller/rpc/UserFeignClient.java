package com.jay.test.springcloud.controller.rpc;

import com.jay.test.springcloud.model.UacUser;
import com.jay.test.springcloud.service.UacUserService;
import com.jay.test.springcloud.service.UserFeignApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/8/21 9:55
 * @Modified by :
 */
@RestController
public class UserFeignClient implements UserFeignApi{
    @Autowired
    UacUserService uacUserService;

    @Override
    public UacUser selectOne(UacUser uacUser) {
        return uacUserService.selectOne(uacUser);
    }
}
