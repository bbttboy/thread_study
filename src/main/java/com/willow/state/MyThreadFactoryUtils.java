package com.willow.state;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author MrWillow
 */
public class MyThreadFactoryUtils implements ThreadFactory {

    private String prefix = null;
    private String suffix = null;
    private AtomicInteger start = null;

    public MyThreadFactoryUtils() {
        this.prefix = "";
        this.suffix = "";
        start = new AtomicInteger();
    }

    public MyThreadFactoryUtils(String prefix, String suffix, Integer start) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.start = new AtomicInteger(start);
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, prefix + start.getAndIncrement() + suffix);
    }
}
