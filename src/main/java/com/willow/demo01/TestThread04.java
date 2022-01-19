package com.willow.demo01;

/**
 * 多线程访问同一资源，买票
 *
 * @author MrWillow
 */
public class TestThread04 implements Runnable{

    private volatile Integer ticketNums = 10;

    @Override
    public void run() {

        while (ticketNums > 0) {
            Integer temp = ticketNums;
            synchronized (ticketNums) {
                // CAS
                if (ticketNums.equals(temp)) {
                    System.out.println(Thread.currentThread().getName() +
                            " --> 拿到了第" + temp + "张票！");
                    ticketNums = temp - 1;
                }
            }
            try {
                Thread.sleep(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TestThread04 station = new TestThread04();

        new Thread(station, "小明").start();
        new Thread(station, "小张").start();
        new Thread(station, "小李").start();
    }
}
