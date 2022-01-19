package com.willow;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 信号量
 * @author MrWillow
 */
public class TestPC2 {

    public static void main(String[] args) {
        Tv tv = new Tv();
        Performer p = new Performer(tv);
        Audience a = new Audience(tv);
        p.start();
        a.start();
    }
}

/**
 * 生产者 -> 演员
 */
class Performer extends Thread{
    private Tv tv;
    public Performer(Tv tv) {
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
class Audience extends Thread{
    private Tv tv;
    public Audience(Tv tv) {
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
class Tv {

    /**
     * 表演的节目
     */
    String programme;
    /**
     * 标志位<p>
     * 节目是否准备好
     */
    private final AtomicBoolean flag = new AtomicBoolean(false);
    private final Lock lock = new ReentrantLock();

    public void act(String programme) {
        lock.lock();
        try {
            // 如果观众在看 演员等待
            while (flag.get()) {
                this.wait();
            }
            this.programme = programme;
            System.out.println("【" + Thread.currentThread().getName() + "】"
                    + "表演了" + "\"" + programme + "\"");
            flag.set(!flag.get());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            this.notify();
        }
    }

    public void watch() {
        lock.lock();
        try {
            // 演员在表演 观众等待
            while (!flag.get()) {
                this.wait();
            }
            System.out.println("【" + Thread.currentThread().getName() + "】"
                    + "观看了" + "\"" + programme + "\"");
            flag.set(!flag.get());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            this.notify();
        }
    }
}
