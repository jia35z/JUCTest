package com.lijia.juc.completableFurure;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CompletableFuture的四个静态方法的简单使用
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        StaticMethodsUse();

    }

    /**
     * CompletableFuture的静态方法的接收
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void StaticMethodsUse() throws InterruptedException, ExecutionException {
    /*
        runAsync():没有返回值接收，携带了线程池。就用自己的。没有携带就是使用默认的ForkJoinPool.commonPool
     */
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("runAsync method run ... 未携带线程池");
        });
        System.out.println(voidCompletableFuture.get());


        CompletableFuture<Void> voidCompletableFutureWhitThreadPool = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("runAsync method run ... 携带线程池");
        }, threadPool);
        System.out.println(voidCompletableFutureWhitThreadPool.get());

        /*
            supplyAsync():有返回值参数，携带了线程池。就用自己的。没有携带就是使用默认的ForkJoinPool.commonPool
         */

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("supplyAsync method run ... 未携带线程池");
            return "supplyAsync method 未携带线程池";
        });
        System.out.println(stringCompletableFuture.get());


        CompletableFuture<String> stringCompletableFutureWith = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("supplyAsync method run ... 携带线程池");
            return "supplyAsync method 携带线程池";
        }, threadPool);
        System.out.println(stringCompletableFutureWith.get());


        threadPool.shutdown();
        System.out.println("dev test merge");
    }


}
