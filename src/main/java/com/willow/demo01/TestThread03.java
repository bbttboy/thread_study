package com.willow.demo01;

/**
 * 线程创建方式二：实现runnable接口
 * 实现run()方法，执行线程需要给Thread类传入实现接口的类，然后调用start()方法
 *
 * @author MrWillow
 */
public class TestThread03 implements Runnable{

    public static final Integer LOOP = 20;

    @Override
    public void run() {
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
        TestThread03 thread03 = new TestThread03();

        // 调用start方法开启线程
        // 创建线程对象，通过线程对象来开启线程，
        // 静态代理！！！！
        new Thread(thread03).start();

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
