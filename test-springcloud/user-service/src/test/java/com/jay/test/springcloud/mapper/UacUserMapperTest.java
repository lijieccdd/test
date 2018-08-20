package com.jay.test.springcloud.mapper;

import com.jay.test.springcloud.UserServiceApplication;
import com.jay.test.springcloud.model.UacUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/8/20 10:32
 * @Modified by :
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServiceApplication.class)
public class UacUserMapperTest {
    @Autowired
    private UacUserMapper uacUserMapper;

    @Test
    public void testSelectUser(){
        List<UacUser> userList = uacUserMapper.selectAll();
        for (UacUser uacUser : userList) {
            System.out.println(uacUser);
        }
    }
}
