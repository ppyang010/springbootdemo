package com.code;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;

import java.util.concurrent.TimeUnit;

/**
 * @author ccy
 * @description
 * @time 2020-09-22 16:30
 */
public class ZookeeprLockDemo {

    public static void main(String[] args) {
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.190.103:2181", new RetryNTimes(10, 5000));
        client.start();// 连接
        InterProcessMutex locks = new InterProcessMutex(client, "locks");
        try {
            locks.acquire(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
