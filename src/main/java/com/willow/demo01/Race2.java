package com.willow.demo01;

/**
 * 模拟龟兔赛跑
 *
 * @author MrWillow
 */
public class Race2 implements Runnable{

    private final Integer LENGTH = 100;
    private static String winner;
    String tu = "兔子";

    @Override
    public void run() {
        for (Integer i = 1; i <= LENGTH; i++) {

            if (Thread.currentThread().getName().equals("兔子") && i % 10 == 0) {
                System.out.println("兔子在睡觉！");
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // 判断游戏是否结束
            Boolean flag = gameOver(i);
            if (flag) {
                break;
            }
            System.out.println(Thread.currentThread().getName()
                    + " --> 跑了" + i + "步");
        }
    }

    private boolean gameOver(Integer step) {
        if (null != winner) {
            return true;
        }

        if (step >= LENGTH) {
            winner = Thread.currentThread().getName();
            System.out.println("Winner is " + winner);
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Race2 rabbit = new Race2();
        Race2 turtle = new Race2();

        new Thread(rabbit, "兔子").start();
        new Thread(turtle, "乌龟").start();
    }
}
