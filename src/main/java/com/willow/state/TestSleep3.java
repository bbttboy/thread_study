package com.willow.state;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 打印当前时间
 * @author MrWillow
 */
public class TestSleep3 {

    public static void main(String[] args) throws InterruptedException {
        Date now;
        while (true) {
            now = new Date(System.currentTimeMillis());
            // 24小时 HH
            // 12小时 hh
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(now));
            Thread.sleep(1000);
        }
    }
}
