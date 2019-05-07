import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * finalize 测试
 * @author ccy
 */
public class TFinal {
    public static TFinal obj ;


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize方法被执行!");
        obj = this;
    }

    public static void testFinalize() throws InterruptedException {
        TFinal.obj = new TFinal();
        TFinal.obj = null;
        //进行第一次gc
        System.gc();
        //因为finalize方法优先级很低 等待它
        Thread.sleep(500);
        if(obj != null){
            System.out.println("1-obj还存活着！");
        }else{
            System.out.println("1-obj已经死了！");
        }
        TFinal.obj = null;
        //进行第二次gc
        System.gc();
        //因为finalize方法优先级很低 等待它
        Thread.sleep(500);
        if(obj != null){
            System.out.println("2-obj还存活着！");
        }else{
            System.out.println("2-obj已经死了！");
        }


    }

    public static void main(String[] args) throws InterruptedException {
//        testFinalize();
//        map();
//        -------
//        BigDecimal bigDecimal = new BigDecimal("1.00");
//        DecimalFormat decimalFormat = new DecimalFormat("0.##");
//        String format = decimalFormat.format(bigDecimal);
//        System.out.println(format);
//        Random random = new Random();
//        random.nextInt()
        //----------
//        map2();
        //------
//        atom();
        //----
        deadLock();
        AtomicInteger i= null;
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
        Condition condition = lock.newCondition();
        condition.await();
        condition.signal();

        CountDownLatch latch = new CountDownLatch(5);
        latch.countDown();
        latch.await();
        Semaphore semaphore = new Semaphore(10);
        semaphore.acquire();
        semaphore.release();

        ExecutorService executorService = Executors.newCachedThreadPool();

    }

    public static void map(){
        HashMap<Object, Object> map = new HashMap<>();
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        map.put("4","4");
        map.put("5","5");
        System.out.println(map.size());
    }


    public static void map2(){
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        map.put("1",2);
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("1",1);
        linkedHashMap.put("2",2);
        linkedHashMap.put("3",3);
        linkedHashMap.put("4",4);
        linkedHashMap.forEach((m1,m2)->{
            System.out.println(String.format("m1:%s,m2:%s",m1.toString(),m2));
        });
    }


    public static void atom(){
        AtomicInteger atomicInteger = new AtomicInteger(10);
        atomicInteger.addAndGet(1);
        System.out.println(atomicInteger);

    }


    public static void deadLock(){
        Object lockA = new Object();
        Object locKB = new Object();


        Thread thread1 = new Thread(() -> {
            System.out.println("线程1-尝试获取lockA");
            synchronized (lockA) {
                System.out.println("线程1-获取lockA");
                System.out.println("线程1-sleep");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程1-尝试获取lockB");
                synchronized (locKB) {
                    System.out.println("线程1-获取lockB");
                    System.out.println("线程1");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("线程2-尝试获取lockB");
            synchronized (locKB) {
                System.out.println("线程2-获取lockB");
                System.out.println("线程2-sleep");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程2-尝试获取lockA");
                synchronized (lockA) {
                    System.out.println("线程1-获取lockA");
                    System.out.println("线程2");
                }
            }
        });

        thread1.start();
        thread2.start();

    }

}
