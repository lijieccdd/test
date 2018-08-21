package com.jay.test.springcloud.service.impl;

import com.jay.test.springcloud.model.Cart;
import com.jay.test.springcloud.model.UacUser;
import com.jay.test.springcloud.service.CartService;
import com.jay.test.springcloud.service.UserFeignApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/8/21 9:47
 * @Modified by :
 */
@Service
public class CartServiceImpl implements CartService{
    @Autowired
    UserFeignApi userFeignApi;

    @Override
    public Cart selectOne(Cart cart) {
        UacUser uacUser = new UacUser();
        uacUser.setUserCode("admin");
        UacUser selUacUser = userFeignApi.selectOne(uacUser);
        return null;
    }
}
