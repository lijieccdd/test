package com.jay.test.springboot.mapper;

import com.jay.test.springboot.common.BaseMapper;
import com.jay.test.springboot.model.user.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {
    public User selectByCardNo(@Param("cardNo") int cardNo);
}
