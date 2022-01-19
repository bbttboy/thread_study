package com.willow.syn;

import com.willow.state.MyThreadFactoryUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 不安全的ArrayList
 * @author MrWillow
 */
public class UnsafeList {

    public static void main(String[] args) throws InterruptedException {

        List<String> list = new ArrayList<>();

        MyThreadFactoryUtils myFactory = new MyThreadFactoryUtils("MyThread .. ", "!", 1);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(100, Integer.MAX_VALUE,
                100, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>(), myFactory);
        for (int i = 0; i < 10000; i++) {
            Runnable r = () -> {list.add(Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName());};
            executor.execute(r);
            System.out.println("ActiveCount: " + executor.getActiveCount());
            System.out.println("TaskCount: " + executor.getTaskCount());
        }
        executor.shutdown();
        Thread.sleep(1000);
        System.out.println("size: " + list.size());
    }
}
