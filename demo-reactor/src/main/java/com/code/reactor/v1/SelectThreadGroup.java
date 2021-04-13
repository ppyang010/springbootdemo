package com.code.reactor.v1;

/**
 * @author ccy
 * @description
 * @time 2021/4/13 5:21 下午
 */
public class SelectThreadGroup {
    private SelectThread[] threads;

    public SelectThreadGroup(int num, String threadName) {
        threads = new SelectThread[num];
        for (int i = 0; i < num; i++) {
            threads[i] = new SelectThread(this);
            new Thread(threads[i], threadName).start();
        }
    }
}
