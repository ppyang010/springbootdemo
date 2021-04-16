package com.code.reactor.v1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程组管理
 *
 * @author ccy
 * @description
 * @time 2021/4/13 5:21 下午
 */
public class SelectThreadGroup {
    private SelectThread[] threads;

    private ServerSocketChannel server = null;

    private AtomicInteger xid = new AtomicInteger(-1);


    public SelectThreadGroup(int num, String threadName) {
        threads = new SelectThread[num];
        for (int i = 0; i < num; i++) {
            threads[i] = new SelectThread(this);
            new Thread(threads[i], threadName +"--"+ i).start();
        }
    }

    public void bind(int port) {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));

            //注册到selector上去
            //todo
            nextAndRegisteredSelector(server);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nextAndRegisteredSelector(Channel c) {
        SelectThread st = next();
        //1.通过队列传递数据
        st.getLbq().add(c);
        st.getSelector().wakeup();
    }

    /**
     * (根据算法)选择下一个selector
     *
     * @return
     */
    private SelectThread next() {
        //轮询选择selector 缺点 会有倾斜
        int index = xid.incrementAndGet() % threads.length;
        return threads[index];
    }
}
