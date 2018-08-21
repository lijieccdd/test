package com.jay.test.springcloud.model;

import lombok.Data;

import javax.persistence.Table;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/8/21 9:24
 * @Modified by :
 */
@Data
@Table(name = "pc_omc_cart")
public class Cart {
    private Long userId;
    private Long productId;
    private Integer quantity;
}
