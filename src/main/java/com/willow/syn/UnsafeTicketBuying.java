package com.willow.syn;

/**
 * 不安全的买票
 * @author MrWillow
 */
public class UnsafeTicketBuying {

    public static void main(String[] args) {

        TicketBuy station = new TicketBuy();
        new Thread(station, "小明").start();
        new Thread(station, "小红").start();
        new Thread(station, "小张").start();
    }
}

class TicketBuy implements Runnable {

    /**
     * 票
     */
    private int ticketNums = 10;
    /**
     * 停止标志位
     */
    private boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            buy();
        }
    }

    /**
     * 同步锁，锁的是当前对象this
     */
    public void buy() {
        if (ticketNums <= 0) {
            flag = false;
            return;
        } else {
            // 加延时 等待其他线程  可以放大问题
            // 一般加在 共享资源修改前 共享资源边界判断后
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()
                    + "买到了第" + ticketNums-- + "张票");
        }
    }
}
