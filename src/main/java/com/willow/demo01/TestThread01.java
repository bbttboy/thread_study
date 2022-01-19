package com.willow.demo01;

/**
 * 创建线程方式一：继承Thread类，重写run方法，调用start方法开启线程
 *
 * @author MrWillow
 */
public class TestThread01 extends Thread {

    public static final Integer LOOP = 20;

    @Override
    public void run() {
        // run方法，线程体
        for (int i = 0; i < LOOP; i++) {

            System.out.println("run方法：" + i + "-----------");

            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        // 创建一个线程对象
        TestThread01 thread01 = new TestThread01();

        // 调用start方法开启线程
        thread01.run();
        thread01.start();

        // main方法，主线程
        for (int i = 0; i < LOOP; i++) {
            System.out.println("main方法：" + i + "-----------");

            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
