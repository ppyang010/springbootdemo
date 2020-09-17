package com.code;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.security.cert.Certificate;

/**
 * @author ccy
 * @description
 * @time 2020-08-19 14:12
 */
public class MyClassLoader extends ClassLoader {
    private static String classname = "java.lang.math";

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

        String uri = classLoader.getResource("").getPath() + "/java/lang/" + "Math.class";
//        String uri = classLoader.getResource("").getPath() + "/com/code/" + "Test.class";
        File file = new File(uri);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteOutputStream out = new ByteOutputStream();
            out.write(fileInputStream);

            return defineClass(out.getBytes(), 0, out.getCount());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }




    private void myDefineClass(byte[] b, int off, int len) {

        String source = defineClassSourceLocation(defaultDomain);
        Class<?> c = defineClass1(classname, b, off, len, defaultDomain, source);
        postDefineClass(c, defaultDomain);
        return c;
    }


    // The "default" domain. Set as the default ProtectionDomain on newly
    // created classes.
    private final ProtectionDomain defaultDomain =
            new ProtectionDomain(new CodeSource(null, (Certificate[]) null),
                    null, this, null);

    private String defineClassSourceLocation(ProtectionDomain pd)
    {
        CodeSource cs = pd.getCodeSource();
        String source = null;
        if (cs != null && cs.getLocation() != null) {
            source = cs.getLocation().toString();
        }
        return source;
    }

    private void postDefineClass(Class<?> c, ProtectionDomain pd)
    {
        if (pd.getCodeSource() != null) {
            Certificate certs[] = pd.getCodeSource().getCertificates();
            if (certs != null)
                setSigners(c, certs);
        }
    }

}
