package com.jay.test.springcloud.model;

import lombok.Data;

import javax.persistence.Table;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/8/20 10:10
 * @Modified by :
 */
@Data
@Table(name = "pc_uac_user")
public class UacUser {
    private String loginName;
    private String loginPwd;
    private String userCode;
}
