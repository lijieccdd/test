package com.jay.test.springboot.model.rank;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/7/2 10:32
 * @Modified by :
 */
@Data
public class RankInfo {
    @Excel(name = "比赛名称", height = 10, width = 30, isImportField = "true_st")
    private String matchName;
    @Excel(name = "游戏标识", height = 10, width = 30, isImportField = "true_st")
    private String gameSign;
    @Excel(name = "场次", height = 10, width = 30, isImportField = "true_st")
    private Long screeningId;
    @Excel(name = "场次开始时间", height = 10, width = 30,databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm:ss",
            isImportField = "true_st")
    private Date screeningStartTime;
    @Excel(name = "用户ID", height = 10, width = 30, isImportField = "true_st")
    private Long userId;
    @Excel(name = "排名", height = 10, width = 30, isImportField = "true_st")
    private Integer rank;
    @Excel(name = "积分", height = 10, width = 30, isImportField = "true_st")
    private Integer rankScore;
}
