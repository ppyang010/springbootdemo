package com.code;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;

/**
 * @author ccy
 * @description
 * @time 2020-08-19 14:12
 */
public class MyClassLoader extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
//        Semaphore one = new Semaphore(0);
//        Semaphore two = new Semaphore(0);
//        two.release();
//        two.acquire();
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
//        CountDownLatch countDownLatch = new CountDownLatch(10);
//        cyclicBarrier.await()
//        HashSet<Object> objects = new HashSet<>();
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        System.out.println(classLoader);

//        String uri = classLoader.getResource("").getPath() + "/java/lang/" + "Math.class";
        String uri = classLoader.getResource("").getPath() + "/com/code/" + "Test.class";
        File file = new File(uri);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteOutputStream out = new ByteOutputStream();
            out.write(fileInputStream);

            return defineClass(name, out.getBytes(), 0, out.getCount());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }


}
