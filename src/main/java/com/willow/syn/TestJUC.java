package com.willow.syn;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author MrWillow
 */
public class TestJUC {

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new CopyOnWriteArrayList<String>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> list.add(Thread.currentThread().getName())).start();
        }
        Thread.sleep(1000);
        System.out.println(list.size());
    }
}
