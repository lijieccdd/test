package com.jay.testspringboot.model.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/8/1 10:49
 * @Modified by :
 */
@Data
public class User {
    private Long id;

    @NotEmpty(message="用户名不能为空")
    @Length(min=6,max = 12,message="用户名长度必须位于6到12之间")
    private String name;

    private String addr;
    @Email(message="请输入正确的邮箱")
    private String email;
    @Pattern(regexp = "^(\\d{18,18}|\\d{15,15}|(\\d{17,17}[x|X]))$", message = "身份证格式错误")
    private String idCard;
}
