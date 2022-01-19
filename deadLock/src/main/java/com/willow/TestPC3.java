package com.willow;

public class TestPC3 {
    public static void main(String[] args) {
        Tv1 tv = new Tv1();
        Performer1 p = new Performer1(tv);
        Audience1 a = new Audience1(tv);
        p.start();
        a.start();
    }
}

/**
 * 生产者 -> 演员
 */
class Performer1 extends Thread{
    private Tv1 tv;
    public Performer1(Tv1 tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        int end = 20;
        for (int i = 0; i < end; i++) {
            if (i % 2 == 0) {
                this.tv.act("胸口碎大石");
            } else {
                this.tv.act("铁锅炖自己");
            }
        }
    }
}

/**
 * 消费者 -> 观众
 */
class Audience1 extends Thread{
    private Tv1 tv;
    public Audience1(Tv1 tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        int end = 20;
        for (int i = 0; i < end; i++) {
            this.tv.watch();
        }
    }
}
/**
 * 产品 -> 电视节目<p>
 * 演员在线下表演录制，观众等待 <p>
 * 观众观看，演员休假进行等待 <p>
 */
class Tv1 {

    /**
     * 表演的节目
     */
    String programme;
    /**
     * 标志位<p>
     * 节目是否准备好
     */
    private boolean flag = false;

    public synchronized void act(String programme) {
        // 节目准备好了 不表演
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 节目没有准备好 演员表演节目
        System.out.println("【" + Thread.currentThread().getName() + "】"
                + "表演了" + "\"" + programme + "\"");

        // 通知观众
        this.notifyAll();
        this.flag = !this.flag;
        this.programme = programme;
    }

    public void watch() {
        // 节目没准备好了 等待表演
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 节目准备好 观看节目
        System.out.println("【" + Thread.currentThread().getName() + "】"
                + "观看了" + "\"" + programme + "\"");

        // 通知演员
        this.notifyAll();
        this.flag= !this.flag;
    }
}
