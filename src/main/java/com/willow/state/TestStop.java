package com.willow.state;

/**
 * 测试 stop <p>
 * 1.建议线程正常停止 ---> 使用次数，不建议死循环 <p>
 * 2.建议使用标志位 <p>
 * 3.不使用 stop / destroy 等过时的 或者 jdk 不建议的方法 <p>
 * @author MrWillow
 */
public class TestStop implements Runnable{

    boolean flag = true;
    private static final Integer LOOP = 1000;
    private static final Integer THRESHOLD = 888;

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("run .... thread " + i++);
        }
    }

    public void stop() {
        flag = false;
    }

    public static void main(String[] args) {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();

        for (int i = 0; i < LOOP; i++) {
            System.out.println("main ... " + i);
            if (i == THRESHOLD) {
                // 调用stop切换标志位，让线程停止
                testStop.stop();
                System.out.println("~~~~~~~~~~~~~线程停止~~~~~~~~~~~~~");
            }
        }
    }
}
