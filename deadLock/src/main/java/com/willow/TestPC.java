package com.willow;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 消费者生产者 --> 利用缓存区(管程法)
 * @author MrWillow
 */
public class TestPC {

    public static void main(String[] args) {
        SynContainer container = new SynContainer();
        new Thread(new Producer(container), "生产者1").start();
        new Thread(new Producer(container), "生产者2").start();
        new Thread(new Producer(container), "生产者3").start();
        new Thread(new Producer(container), "生产者4").start();
        new Thread(new Consumer(container), "消费者1").start();
        new Thread(new Consumer(container), "消费者2").start();
    }
}

class Producer implements Runnable {

    private SynContainer container;
    private static final AtomicInteger count = new AtomicInteger(1);

    public Producer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        int end = 100;
        for (int i = 1; i < end; i++) {
            Chicken chicken = new Chicken();
            chicken.id = count.getAndIncrement();
            container.push(chicken);
        }
    }
}

class Consumer implements Runnable {

    private SynContainer container;

    public Consumer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        int end = 199;
        for (int i = 1; i < end; i++) {
            container.pop();
        }
    }
}

class Chicken {
    public int id;
}

class SynContainer {

    /**
     * 需要一个容器大小
     */
    private final Chicken[] chickens = new Chicken[10];
    /**
     * 容器内计数器
     */
    private Integer count = 0;

    public synchronized void push(Chicken chicken) {

        // 缓冲区满了 不允许生产
        // 不能用if 有脏判断 因为线程被唤醒后还需要进行判断
        while (count >= chickens.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("【" + Thread.currentThread().getName()
                +"】生产了 --> 第" + chicken.id + "只鸡");
        chickens[count] = chicken;
        count++;
        this.notifyAll();
    }

    public synchronized void pop() {

        // 缓冲区空了 不允许消费
        while (count <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        count--;
        Chicken chicken = chickens[count];
        chickens[count] = null;
        System.out.println("【"+ Thread.currentThread().getName()
                +"】消费了 --> 第" + chicken.id + "只鸡");
        this.notifyAll();
    }
}