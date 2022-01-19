package com.willow.demo01;

/**
 * 模拟龟兔赛跑
 *
 * @author MrWillow
 */
public class Race implements Runnable{

    private final int[] flag = new int[]{0};

    @Override
    public void run() {
        int d = 2;
        int end = 100;
        for (int i = 1; i < end * d; i++) {
            synchronized (flag) {
                if (flag[0] == 1) {
                    break;
                } else {
                    if (i >= end) {
                        flag[0] = 1;
                        System.out.println(Thread.currentThread().getName() + " -> 跑了" + i + "步");
                        System.out.println(Thread.currentThread().getName() + " -> 胜利！！！！！！！！！！！！！！！！！！");
                    } else {
                        System.out.println(Thread.currentThread().getName() + " -> 跑了" + i + "步");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race, "乌龟").start();
        new Thread(race, "兔子").start();
    }
}
