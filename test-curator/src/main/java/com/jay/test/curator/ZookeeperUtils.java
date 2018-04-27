package com.jay.test.curator;

import com.jay.test.curator.constant.CommConstant;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/4/17 15:25
 * @Modified by :
 */
public class ZookeeperUtils {
    private static CuratorFramework client;
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

        //第二种方式
        CuratorFramework client1 = CuratorFrameworkFactory.builder().connectString("47.94.87.93:2181")
                .sessionTimeoutMs(5000)//会话超时时间
                .connectionTimeoutMs(5000)//连接超时时间
                .retryPolicy(retryPolicy)
                .build();
        client1.start();

        /*String path = client1.create().creatingParentsIfNeeded()//若创建节点的父节点不存在会先创建父节点再创建子节点
                .withMode(CreateMode.EPHEMERAL)//withMode节点类型，
                .forPath("/curator/3","131".getBytes());
        System.out.println(path);*/

        client1.delete().guaranteed()//保障机制，若未删除成功，只要会话有效会在后台一直尝试删除
                .deletingChildrenIfNeeded()//若当前节点包含子节点
                .withVersion(-1)//指定版本号
                .forPath(CommConstant.LOCK_ZNODE);

        List<String> list = client1.getChildren().forPath("/");
        System.out.println(list);
    }


}
