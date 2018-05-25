package com.jay.test.springboot.model.user;

import com.jay.test.springboot.common.BaseModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/5/17 16:55
 * @Modified by :
 */
@Data
@Table(name = "t_user")
public class User extends BaseModel{
    @Column
    private String name;
    @Column
    private Integer age;
    @Column
    private Integer cardNo;
}
