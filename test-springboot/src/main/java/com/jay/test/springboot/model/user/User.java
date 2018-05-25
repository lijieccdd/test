package com.jay.test.springboot.model.user;

import com.jay.test.springboot.model.BaseModel;
import lombok.Data;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/5/17 16:55
 * @Modified by :
 */
@Data
public class User extends BaseModel{
    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
