package com.lijia.juc.completableFurure;

import java.util.concurrent.*;

/**
 * 结合线程池与FutureTask 了解优缺点
 * 三个任务执行所需要的时间
 *
 * 优点 --> future+线程池异步执行，可以大大提高执行效率
 * 缺点 --> get()方法阻塞
 */
public class FutureTaskPThreadPoolTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        long start = System.currentTimeMillis();
        FutureTask task1 = new FutureTask<String>(() -> {
            //TimeUnit.SECONDS.sleep(3);
            Thread.sleep(500);
            return "hallo task1";
        });
        FutureTask<String> task2 = new FutureTask<>(() -> {
            Thread.sleep(300);
            return "hallo task2";
        });

        //Thread t1 = new Thread(task1);
        //t1.start();

        threadPool.submit(task1);
        threadPool.submit(task2);

        System.out.println(task1.get());
        System.out.println(task2.get());


        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        System.out.println("-----cost time" + (end - start) + "毫秒");
        System.out.println(Thread.currentThread().getName() + "\t ----end");
        threadPool.shutdown();
    }


}
