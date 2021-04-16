package com.code.reactor.v2;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author ccy
 * @description
 * @time 2021/4/13 4:30 下午
 */
@Getter
@Setter
@Slf4j
public class SelectThread implements Runnable {


    private Selector selector = null;
    /**
     * 自身所属的group
     */
    private SelectThreadGroup stg = null;


    /**
     * 存放需要注册到这个selector上的客户端
     */
    private LinkedBlockingQueue<Channel> lbq = new LinkedBlockingQueue<>();


    public SelectThread(SelectThreadGroup stg) {
        try {
            this.stg = stg;
            this.selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                int num = selector.select();
                // 处理selector key
                // >0 代表要处理的事件
                if (num > 0) {
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isAcceptable()) {
                            acceptHandler(key);
                        } else if (key.isReadable()) {
                            readHandler(key);
                        } else if (key.isWritable()) {

                        }
                    }
                }

                // 处理队列中的 listen ,client
                if (!lbq.isEmpty()) {
                    Channel channel = lbq.take();
                    if (channel instanceof ServerSocketChannel) {
                        ServerSocketChannel server = (ServerSocketChannel) channel;
                        server.register(selector, SelectionKey.OP_ACCEPT);
                        log.info("register listen");
                    } else if (channel instanceof SocketChannel) {
                        SocketChannel client = (SocketChannel) channel;
                        ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
                        client.register(selector, SelectionKey.OP_READ, buffer);
                        log.info("register client {}", client.getRemoteAddress());
                        ByteBuffer allocate = ByteBuffer.allocate(1024);
                        allocate.put("Connection successful".getBytes());
                        allocate.flip();
                        client.write(allocate);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void readHandler(SelectionKey key) {
        log.info("read.......");
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        SocketChannel client = (SocketChannel) key.channel();
        buffer.clear();
        while (true) {
            try {
                int num = client.read(buffer);
                if (num > 0) {
                    buffer.flip();  //将读到的内容翻转，然后直接写出
                    while (buffer.hasRemaining()) {
                        client.write(buffer);
                    }
                    buffer.clear();
                } else if (num == 0) {
                    break;
                } else if (num < 0) {
                    //客户端断开了
                    log.info("client: {} closed....", client.getRemoteAddress());
                    key.cancel();
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void acceptHandler(SelectionKey key) {
        //接受客户端  接受 注册
        log.info("acceptHandler........");
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        SocketChannel client = null;
        try {
            //接受客户端
            client = channel.accept();
            client.configureBlocking(false);

            //注册
            // choose a selector and register
            stg.nextAndRegisteredSelector(client);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
