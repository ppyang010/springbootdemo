package com.code.reactor.v2;

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
    /**
     * group 管理的selector
     */
    private SelectThread[] threads;

    private ServerSocketChannel server = null;

    private AtomicInteger xid = new AtomicInteger(-1);

    /**
     * 如果group为boss 需要设置对应的work
     * 如果group为work
     */
    private SelectThreadGroup workGroup = null;


    public SelectThreadGroup(int num, String threadName) {
        threads = new SelectThread[num];
        for (int i = 0; i < num; i++) {
            threads[i] = new SelectThread(this);
            new Thread(threads[i], threadName + "--" + i).start();
        }
        this.workGroup = this;


    }

    public SelectThreadGroup setWorkGroup(SelectThreadGroup workGroup) {
        this.workGroup = workGroup;
        return this;
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
        if(c instanceof ServerSocketChannel){
            //listen 需要 选择bossgroup 中的 selector 注册
            SelectThread st = next();
            //1.通过队列传递数据
            st.getLbq().add(c);
            st.getSelector().wakeup();
        }else {
            //client 需要选择wrok group 中的selector 注册
            SelectThread st = nextFromWork();
            //1.通过队列传递数据
            st.getLbq().add(c);
            st.getSelector().wakeup();
        }

    }

    /**
     *
     * (根据算法)选择下一个selector
     * v2 chang: 从当前group中管理的selector集合中选择
     * 修改为如果是作为boss 则在其work group 中选择
     * 如果是作为work 则仍在自己的group中选择
     *
     * @return
     */
    private SelectThread nextFromWork() {
        //轮询选择selector 缺点 会有倾斜
        SelectThread[] threads = this.workGroup.getThreads();
        int index = xid.incrementAndGet() % threads.length;
        return threads[index];
    }

    private SelectThread next() {
        //轮询选择selector 缺点 会有倾斜
        int index = xid.incrementAndGet() % threads.length;
        return threads[index];
    }


    public SelectThread[] getThreads() {
        return threads;
    }

    public SelectThreadGroup setThreads(SelectThread[] threads) {
        this.threads = threads;
        return this;
    }
}
