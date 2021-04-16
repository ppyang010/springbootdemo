package com.code.reactor.v1;

/**
 * @author ccy
 * @description
 * @time 2021/4/13 4:23 下午
 */
public class MainThread {
    public static void main(String[] args) {
        //1.创建Io Thread 组
        // listen 和 client 混合模式
        SelectThreadGroup threadGroup = new SelectThreadGroup(3, "work");
        //2.绑定端口号 listen port
        threadGroup.bind(9999);
    }
}
