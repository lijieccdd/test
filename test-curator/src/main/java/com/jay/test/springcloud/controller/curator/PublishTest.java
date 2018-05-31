package com.jay.test.springcloud.controller.curator;

import com.jay.test.springcloud.controller.curator.constant.CommConstant;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.utils.ZKPaths;

import java.util.concurrent.CountDownLatch;

public class PublishTest {
    static CuratorFramework client = null;
    static CountDownLatch countDownLatch = new CountDownLatch(1);

    /**
     * 客户端初始化
     * @throws Exception
     */
    public static void init() throws Exception {
        client = CuratorFrameworkFactory.builder().connectString(CommConstant.CONNECT_URL)
                .sessionTimeoutMs(CommConstant.TIMEOUT)
                .retryPolicy(new RetryNTimes(5, 5000)).build();
        // 客户端注册监听，进行连接配置
        client.getConnectionStateListenable().addListener(clientListener);
        client.start();
        // 连接成功后，才进行下一步的操作
        countDownLatch.await();
    }

    /**
     * 客户端重新注册
     */
    public static void reinit() {
        try {
            unregister();
            init();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void unregister() {
        try {
            if (client != null) {
                client.close();
                client = null;
            }
        } catch (Exception e) {
            System.out.println("unregister failed");
        }
    }

    public static void main(String[] args) throws Exception {
        init();
        watcherPath(CommConstant.LOCK_ZNODE);
        Thread.sleep(Integer.MAX_VALUE);
    }


    // 对path进行监听配置
    public static void watcherPath(String path)
            throws Exception {
        //子节点的监听
        PathChildrenCache cache = new PathChildrenCache(client, path, false);
        cache.start();
//       注册监听
        cache.getListenable().addListener(plis);
        //对path路径下所有孩子节点的监听
        TreeCache treeCache=new TreeCache(client, path);
        treeCache.start();
        treeCache.getListenable().addListener(treeCacheListener);

    }

    // 客户端的监听配置
    static ConnectionStateListener clientListener = new ConnectionStateListener() {

        @Override
        public void stateChanged(CuratorFramework client,
                                 ConnectionState newState) {
            if (newState == ConnectionState.CONNECTED) {
                System.out.println("connected established");
                countDownLatch.countDown();
            } else if (newState == ConnectionState.LOST) {
                System.out.println("connection lost,waiting for reconection");
                try {
                    System.out.println("reinit---");
                    reinit();
                    System.out.println("inited---");
                } catch (Exception e) {
                    System.err.println("re-inited failed");
                }
            }

        }
    };

    /**
     * 子节点的监听
     */
    static PathChildrenCacheListener plis = new PathChildrenCacheListener() {

        @Override
        public void childEvent(CuratorFramework client,
                               PathChildrenCacheEvent event) throws Exception {
            switch (event.getType()) {
                case CHILD_ADDED: {
                    System.out
                            .println("Node added: "
                                    + ZKPaths.getNodeFromPath(event.getData()
                                    .getPath()));
                    break;
                }

                case CHILD_UPDATED: {
                    System.out
                            .println("Node changed: "
                                    + ZKPaths.getNodeFromPath(event.getData()
                                    .getPath()));
                    break;
                }

                case CHILD_REMOVED: {
                    System.out
                            .println("Node removed: "
                                    + ZKPaths.getNodeFromPath(event.getData()
                                    .getPath()));
                    break;
                }
            }

        }
    };
    /**
     * 所有子节点的监听
     */
    static TreeCacheListener treeCacheListener=new TreeCacheListener() {

        @Override
        public void childEvent(CuratorFramework client, TreeCacheEvent event)
                throws Exception {
            // TODO Auto-generated method stub
            switch (event.getType()) {
                case NODE_ADDED:
                    System.out
                            .println("TreeNode added: " +event.getData()
                                    .getPath()+" , data: "+new String(event.getData().getData()));
                    break;
                case NODE_UPDATED:
                    System.out
                            .println("TreeNode updated: "+event.getData()
                                    .getPath()+" , data: "+new String(event.getData().getData()));
                    break;
                case NODE_REMOVED:
                    System.out
                            .println("TreeNode removed: "+event.getData()
                                    .getPath());
                    break;
                default:
                    break;
            }
        }
    };
}