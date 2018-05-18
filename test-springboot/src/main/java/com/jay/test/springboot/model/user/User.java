package com.jay.test.springboot.model.user;

import com.jay.test.springboot.model.BaseModel;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/5/17 16:55
 * @Modified by :
 */
@Data
@Entity
@Table(name = "user")
public class User extends BaseModel{
    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
