package com.willow.state;

import java.lang.Thread;

/**
 * 测试守护线程<p>
 * 上帝守护你
 * @author MrWillow
 */
public class TestDaemon {

    public static void main(String[] args) {
        Thread god = new Thread(new God(), "god");
        god.setDaemon(true);
        god.start();

        Thread you = new Thread(new You(), "yl");
        you.start();
    }
}


class God implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("上帝守护着你！！");
        }
    }
}

class You implements Runnable {

    private static final Integer LIFE_TIME = 30000;

    @Override
    public void run() {
        for (Integer i = 0; i < LIFE_TIME; i++) {
            System.out.println("你活了" + i + "天");
        }
        System.out.println("你死了！");
    }
}
