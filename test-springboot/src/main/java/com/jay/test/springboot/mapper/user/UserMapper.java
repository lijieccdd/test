/*
package com.jay.test.springboot.mapper.user;

import com.jay.test.springboot.model.user.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
  @Delete("drop table t_user if exists")
  void dropTable();

  @Insert("create table t_user (id bigint generated by default as identity, age integer, name varchar(255), primary key (id))")
  void createTable();

  @Insert("insert into t_user(name,age) values(#{name},#{age})")
  void insert(User user);

  @Select("select id,name,age from t_user")
  List<User> findAll();

  @Select("select id,name,age from t_user where name like #{name}")
  List<User> findByNameLike(String name);

  @Delete("delete from t_user")
  void deleteAll();

}*/
