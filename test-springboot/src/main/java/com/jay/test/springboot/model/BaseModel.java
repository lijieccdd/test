package com.jay.test.springboot.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/5/17 16:57
 * @Modified by :
 */
@Data
public class BaseModel implements Serializable{
    private Long id;

    public BaseModel() {
    }

    protected boolean canEqual(Object other) {
        return other instanceof BaseModel;
    }

}
