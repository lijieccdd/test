package com.jay.test.user;

import com.github.pagehelper.PageHelper;
import com.jay.test.springboot.AppApplication;
import com.jay.test.springboot.mapper.UserMapper;
import com.jay.test.springboot.model.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppApplication.class)
public class SpringBootMyBatisApplicationTests {

    @Autowired
    private UserMapper mapper;

    // 插入一条新记录
    @Test
    public void insertOne() {
        User newUser = new User();
        int cardNo = (int) (Math.random() * 10000000);
        newUser.setAge(24);
        newUser.setName(cardNo + "用户");
        newUser.setCardNo(cardNo);
        mapper.insert(newUser);
        System.out.println("插入成功");
    }

    // 批量插入记录
    @Test
    public void insertMore() {
        List<User> recordList = new ArrayList<User>();
        for (int i = 0; i < 2; i++) {
            User newUser = new User();
            int cardNo = (int) (Math.random() * 10000000);
            newUser.setAge(26);
            newUser.setName(cardNo + "批量插入用户");
            newUser.setCardNo(cardNo);
            recordList.add(newUser);
        }
        mapper.insertList(recordList);
        System.out.println("批量插入成功");
    }

    // 根据唯一编号查询用户(通用Mapper查询)
    @Test
    public void selectByCardNo() {
        User paramBean = new User();
        User dbUser = mapper.selectOne(paramBean);
        if (dbUser != null) {
            System.out.println("数据库用户(通用Mapper查询)：" + dbUser.getName());
            return;
        }
        System.out.println("查无此用户");
    }

    // 根据唯一编号查询用户(XML查询)
    @Test
    public void selectByCardNoByXml() {
        User dbUser = mapper.selectByCardNo(2844477);
        if (dbUser != null) {
            System.out.println("数据库用户(XML查询)：" + dbUser.getName());
            return;
        }
        System.out.println("查无此用户");
    }

    // 根据年龄查询一组用户
    @Test
    public void selectByAge() {
        User paramBean = new User();
        paramBean.setAge(24);
        List<User> dbUserList = mapper.select(paramBean);
        System.out.println("总共查询数：" + dbUserList.size());
    }

    // 分页查询用户
    @Test
    public void selectByPage() {
        PageHelper.offsetPage(1, 5);
        List<User> dbUserList = mapper.select(null);
        for (User item : dbUserList) {
            System.out.println("分页用户：" + item.getName());
        }
    }

    // 更新用户信息
    @Test
    public void updateOneInfo() {
        User paramBean = new User();
        paramBean.setId(1);
        paramBean.setAge(26);
        mapper.updateByPrimaryKeySelective(paramBean);
        System.out.println("更新成功");
    }

}