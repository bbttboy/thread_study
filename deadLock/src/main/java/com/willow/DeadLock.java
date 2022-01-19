package com.willow;

/**
 * 死锁：互相持有对方需要的锁，且不释放
 * @author MrWillow
 */
public class DeadLock {

    public static void main(String[] args) {
        MakeUp g1 = new MakeUp(0, "白雪公主");
        MakeUp g2 = new MakeUp(1, "灰姑凉");

        new Thread(g1, "g1").start();
        new Thread(g2, "g2").start();
    }
}

class Lipstick {
    static final String NAME = "口红";
}

class Mirror {
    static final String NAME = "镜子";
}

class MakeUp implements Runnable {

    private static Lipstick lipstick = new Lipstick();
    private static Mirror mirror = new Mirror();
    private int choice;
    private String name;

    public MakeUp(int choice, String name) {
        this.choice = choice;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            makeUp();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void makeUp() throws InterruptedException {
        if (choice == 0) {
            synchronized (lipstick) {
                System.out.println(this.name + " 拿到了 " + Lipstick.NAME);
                Thread.sleep(1000);
                synchronized (mirror) {
                    System.out.println(this.name + " 拿到了 " + Mirror.NAME);
                }
            }
        } else {
            synchronized (mirror) {
                System.out.println(this.name + " 拿到了 " + Mirror.NAME);
                Thread.sleep(1000);
                synchronized (lipstick) {
                    System.out.println(this.name + " 拿到了 " + Lipstick.NAME);
                }
            }
        }
    }
}
