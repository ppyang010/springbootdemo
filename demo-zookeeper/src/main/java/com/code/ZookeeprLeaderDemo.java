package com.code;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.retry.RetryNTimes;

/**
 * @author ccy
 * @description
 * @time 2020-09-22 18:06
 */
public class ZookeeprLeaderDemo {

    public static void main(String[] args) {
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.190.103:2181", new RetryNTimes(10, 5000));
        client.start();// 连接
        LeaderLatch leaderLatch = new LeaderLatch(client,"latchPath");
    }
}
