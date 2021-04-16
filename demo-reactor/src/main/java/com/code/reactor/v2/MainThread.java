package com.code.reactor.v2;

/**
 * @author ccy
 * @description
 * @time 2021/4/13 4:23 下午
 */
public class MainThread {
    public static void main(String[] args) {
        //1.创建Io Thread 组
        //只维护 listen
        SelectThreadGroup bossGroup = new SelectThreadGroup(3, "boss");
        //只维护 client
        SelectThreadGroup workGroup = new SelectThreadGroup(3, "work");
        bossGroup.setWorkGroup(workGroup);
        //2.绑定端口号 listen port
        bossGroup.bind(9999);
        bossGroup.bind(8888);
    }
}
