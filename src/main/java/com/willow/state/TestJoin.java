package com.willow.state;

/**
 * @author MrWillow
 */
public class TestJoin implements Runnable{

    private static final Integer THREAD_LOOP = 500;
    private static final Integer THRESHOLD = 123;
    private static final Integer MAIN_LOOP = 300;


    @Override
    public void run() {
        for (int i = 0; i < THREAD_LOOP; i++) {
            System.out.println("VIP驾到 ... " + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        TestJoin testJoin = new TestJoin();
        Thread vip = new Thread(testJoin, "vip");
        vip.start();

        for (int i = 0; i < MAIN_LOOP; i++) {
            System.out.println("Main ... " + i);
            if (i == THRESHOLD) {
                vip.join();
            }
        }
    }
}
