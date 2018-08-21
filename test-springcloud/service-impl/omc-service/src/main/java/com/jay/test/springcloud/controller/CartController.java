package com.jay.test.springcloud.controller;

import com.jay.test.springcloud.model.Cart;
import com.jay.test.springcloud.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/8/21 10:36
 * @Modified by :
 */
@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    CartService cartService;

    @RequestMapping("cartInfo")
    public void cartInfo(Cart cart){
        cartService.selectOne(cart);
    }
}
