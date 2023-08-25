package com.lijia.juc.daemon;

/**
 * 守护线程与用户线程
 */
public class DaemonTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            boolean daemon = Thread.currentThread().isDaemon();
            System.out.println(Thread.currentThread().getName() + "\t 开始运行" + (daemon ? "守护线程" : "用户线程"));
            while (true) {
//                System.out.println("t1线程中的 while(true)");
            }
        }, "t1");
        //设置t1线程为守护线程----必须在start方法前设置
        t1.setDaemon(true);
        t1.start();


        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName() + "\t ---end 主线程");


    }


}
