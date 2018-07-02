package com.jay.test.springboot.mapper;

import com.jay.test.springboot.common.BaseMapper;
import com.jay.test.springboot.model.rank.RankInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/7/2 10:29
 * @Modified by :
 */
public interface RankListMapper extends BaseMapper<RankInfo> {

    List<RankInfo> selectRankInfoList(@Param("userId") Long userId,@Param("startDate") Date startDate,@Param("endDate") Date endDate);
}
