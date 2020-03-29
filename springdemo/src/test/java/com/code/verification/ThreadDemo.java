package com.code.verification;

import java.util.concurrent.*;

public class ThreadDemo {

     static class ThreadClazz extends Thread{

         @Override
         public void run() {
             System.out.println("run");
         }
     }

     static class CallableClazz implements Callable<String>{

         @Override
         public String call() throws Exception {
             return "good";
         }
     }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ThreadClazz thread = new ThreadClazz();
        thread.getState();
        executorService.submit(thread);
        CallableClazz callable = new CallableClazz();
        FutureTask<String> futureTask = new FutureTask<>(callable);
        try {
            futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

}
