package com.code;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooDefs;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author ccy
 * @description
 * @time 2019-10-31 11:54
 */
public class ZookeeperDemo {

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.202.119:2181", new RetryNTimes(10, 5000));
        client.start();// 连接
        // 获取子节点，顺便监控子节点
        List<String> children = client.getChildren().usingWatcher(new CuratorWatcher() {
            @Override
            public void process(WatchedEvent event) throws Exception {
                System.out.println("监控： " + event);
            }
        }).forPath("/");
        System.out.println(children);
        // 创建节点
        String path = "/test_1";
        String result = client.create().withMode(CreateMode.PERSISTENT).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(path, "Data".getBytes());
        System.out.println(result);
        // 设置节点数据
        client.setData().forPath(path, "111".getBytes());
        client.setData().forPath(path, "222".getBytes());
        byte[] pathData = client.getData().forPath(path);
        System.out.println(new String(pathData, StandardCharsets.UTF_8));
        // 删除节点
//        System.out.println(client.checkExists().forPath("/test"));
//        client.delete().withVersion(-1).forPath("/test");
//        System.out.println(client.checkExists().forPath("/test"));
//        client.close();
//        System.out.println("OK！");
    }
}
