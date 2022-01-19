package com.willow;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试 JUC下的Lock
 * @author MrWillow
 */
public class TestLock {
    public static void main(String[] args) {
        TicketWindow tw = new TicketWindow();

        new Thread(tw, "小张").start();
        new Thread(tw, "小红").start();
        new Thread(tw, "小李").start();
    }
}

class TicketWindow implements Runnable {

    private int ticketNums = 10;
    private final ReentrantLock LOCK = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            LOCK.lock();
            try {
                if (ticketNums <= 0) {
                    break;
                }
                System.out.println(Thread.currentThread().getName()
                        + " 拿到了第 " + ticketNums-- + "张票！");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                LOCK.unlock();
            }
        }
    }
}


