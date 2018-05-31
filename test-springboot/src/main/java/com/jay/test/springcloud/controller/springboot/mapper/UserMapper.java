package com.jay.test.springcloud.controller.springboot.mapper;

import com.jay.test.springcloud.controller.springboot.common.BaseMapper;
import com.jay.test.springcloud.controller.springboot.model.user.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {
    public User selectByCardNo(@Param("cardNo") int cardNo);
}
