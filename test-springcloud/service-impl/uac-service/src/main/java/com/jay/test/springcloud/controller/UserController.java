package com.jay.test.springcloud.controller;

import com.jay.test.springcloud.mapper.UacUserMapper;
import com.jay.test.springcloud.model.UacUser;
import com.jay.test.springcloud.service.UacUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/8/20 14:04
 * @Modified by :
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UacUserService uacUserService;

    @RequestMapping("info")
    public UacUser getUserInfo(UacUser uacUser){
        UacUser result = uacUserService.selectOne(uacUser);
        return result;
    }
}
