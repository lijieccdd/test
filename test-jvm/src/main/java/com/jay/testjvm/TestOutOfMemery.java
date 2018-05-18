package com.jay.testjvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 设置-Xmx5m 最大堆内存 -Xms5m 最小堆值
 * Created by lijie on 2018/5/7.
 */
public class TestOutOfMemery {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        while (true){
            list.add("aaaa");
        }
    }
}
