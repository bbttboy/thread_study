package com.willow.state;

import com.willow.demo01.TestThread04;

/**
 * 模拟网络延时（不安全）
 * @author MrWillow
 */
public class TestSleep implements Runnable{

    private Integer ticketNums = 10;

    @Override
    public void run() {
        while (true) {
            if (ticketNums <= 0) {
                break;
            }
            // 模拟延时
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " 拿到了第" + ticketNums-- + "张票");
        }
    }

    public static void main(String[] args) {
        TestSleep testThread04 = new TestSleep();

        new Thread(testThread04, "小明").start();
        new Thread(testThread04, "小张").start();
        new Thread(testThread04, "小李").start();
    }
}
