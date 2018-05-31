package com.jay.test.springcloud.controller.springboot.common;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/5/17 16:57
 * @Modified by :
 */
@Data
public class BaseModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
