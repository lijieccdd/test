package com.jay.test.springcloud.service;

import com.jay.test.springcloud.model.Cart;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/8/21 9:26
 * @Modified by :
 */
public interface CartService {
    Cart selectOne(Cart cart);
}
