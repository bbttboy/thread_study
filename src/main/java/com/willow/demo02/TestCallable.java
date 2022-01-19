package com.willow.demo02;

import com.willow.state.MyThreadFactoryUtils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 符合规范的利用线程池启动线程
 * 1.自定义线程名字
 * 2.自定义线程池参数，方便阅读
 *
 * @author MrWillow
 */
public class TestCallable implements Callable<Boolean> {

    private static Integer ticketNums = 10;

    @Override
    public Boolean call() throws Exception {
        while (ticketNums > 0) {
            synchronized (ticketNums) {
                if (ticketNums > 0 ) {
                    System.out.println(Thread.currentThread().getName() +
                            " --> 拿到了第" + ticketNums-- + "张票");
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyThreadFactoryUtils myFactory = new MyThreadFactoryUtils("MyThread ... ", " ...", 1);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,
                Integer.MAX_VALUE, 0, TimeUnit.NANOSECONDS,
                new DelayQueue(), myFactory);
        TestCallable t1 = new TestCallable();
        TestCallable t2 = new TestCallable();
        Future<Boolean> submit1 = threadPoolExecutor.submit(t1);
        Future<Boolean> submit2 = threadPoolExecutor.submit(t2);

        Boolean aBoolean = submit1.get();

        threadPoolExecutor.shutdown();
    }
}
