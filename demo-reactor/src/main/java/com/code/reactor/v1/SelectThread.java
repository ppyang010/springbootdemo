package com.code.reactor.v1;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author ccy
 * @description
 * @time 2021/4/13 4:30 下午
 */
@Slf4j
public class SelectThread implements Runnable {


    private Selector selector = null;
    private SelectThreadGroup stg = null;

    public  SelectThread(SelectThreadGroup stg){
        this.stg = stg;
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

                        } else if (key.isWritable()) {

                        }
                    }
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

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
