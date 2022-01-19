package com.willow.state;

/**
 * 模拟倒计时
 * @author MrWillow
 */
public class TestSleep2 {

    public static void main(String[] args) throws InterruptedException {
        tenDown();
    }

    public static void tenDown() throws InterruptedException {
        int nums = 10;
        while (true) {
            if (nums <= 0) {
                break;
            }
            System.out.println(nums--);
            Thread.sleep(1000);
        }
    }
}
