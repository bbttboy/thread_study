package com.willow.state;

@SuppressWarnings("all")
/**
 * 观测线程状态
 * @author MrWillow
 */
public class TestState {


    public static void main(String[] args) {

        int loop = 5;

        Thread thread = new Thread(() -> {
            for (int i = 0; i < loop; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("~~~~~~~~结束~~~~~~~~~~~");
        });

        // 开始观测
        Thread.State state = thread.getState();
        // new
        System.out.println("线程状态: " + state);

        thread.start();
        state = thread.getState();
        // runnable
        System.out.println("线程状态: " + state);

        while (state != Thread.State.TERMINATED) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            state = thread.getState();
            System.out.println("线程状态: " + state);
        }
    }
}
