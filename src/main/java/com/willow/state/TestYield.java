package com.willow.state;

/**
 * @author MrWillow
 */
public class TestYield implements Runnable{

    @Override
    public void run() {
        System.out.println("~~~~~~~~~~~" + Thread.currentThread().getName() + "线程开始执行~~~~~~~~~~~");
        Thread.yield();
        System.out.println("~~~~~~~~~~~" + Thread.currentThread().getName() + "线程结束执行~~~~~~~~~~~");
    }

    public static void main(String[] args) {
        TestYield test1 = new TestYield();
        TestYield test2 = new TestYield();

        new Thread(test1, "A").start();
        new Thread(test2, "B").start();
    }
}
