package com.willow.state;

/**
 * 线程优先级
 * @author MrWillow
 */
public class TestPriority {

    public static void main(String[] args) {
        // 主线程优先级
        System.out.println(Thread.currentThread().getName() + " ... " + Thread.currentThread().getPriority());

        Thread t1 = new Thread(new MyPriority(), "t1");
        Thread t2 = new Thread(new MyPriority(), "t2");
        Thread t3 = new Thread(new MyPriority(), "t3");
        Thread t4 = new Thread(new MyPriority(), "t4");
        Thread t5 = new Thread(new MyPriority(), "t5");
        Thread t6 = new Thread(new MyPriority(), "t6");

        t1.start();

        t2.setPriority(3);
        t2.start();

        t3.setPriority(Thread.NORM_PRIORITY);
        t3.start();

        t4.setPriority(7);
        t4.start();

        t5.setPriority(9);
        t5.start();

        t6.setPriority(Thread.MAX_PRIORITY);
        t6.start();
    }
}

class MyPriority implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " ... " + Thread.currentThread().getPriority());
    }
}