package com.jay.test.springcloud.service.impl;

import com.jay.test.springcloud.mapper.UacUserMapper;
import com.jay.test.springcloud.model.UacUser;
import com.jay.test.springcloud.service.UacUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/8/20 15:46
 * @Modified by :
 */
@Service
public class UacUserServiceImpl implements UacUserService{
    @Autowired
    UacUserMapper uacUserMapper;

    @Override
    public UacUser selectOne(UacUser uacUser) {
        return uacUserMapper.selectOne(uacUser);
    }
}
