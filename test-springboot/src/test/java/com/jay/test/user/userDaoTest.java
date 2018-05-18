package com.jay.test.user;

import com.jay.test.springboot.dao.user.UserDao;
import com.jay.test.springboot.model.user.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class userDaoTest {
  @Autowired
  UserDao userDao;

  // 打印出class com.sun.proxy.$Proxy66表示spring注入通过jdk动态代理获取接口的子类
  @Test
  public void proxy() throws Exception {
    System.out.println(userDao.getClass());
  }

  @Test
  public void save() throws Exception {
    for (int i = 0; i < 10; i++) {
      User user = new User("jege" + i, 25 + i);
      userDao.save(user);
    }
  }

  /*@Test
  public void all() throws Exception {
    save();
    Assertions.assertThat(userDao.findAll()).hasSize(10);
  }

  @Test
  public void findByName() throws Exception {
    save();
    Assertions.assertThat(userDao.findByNameLike("jege%")).hasSize(10);
  }*/

  @After
  public void destroy() throws Exception {
    userDao.deleteAll();
  }

}