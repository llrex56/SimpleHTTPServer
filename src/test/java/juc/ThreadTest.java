package juc;

import java.util.concurrent.*;

/**
 * @author luozhenhong
 * @version 1.0
 * 2022/7/16 17:24
 */
public class ThreadTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // extends Thread
        new Thread1().start();

        // implements Runable
        new Thread(() -> System.out.printf("implements Runnable, override run. thread: %s \n", Thread.currentThread())).start();

        // Future
        ExecutorService executor = Executors.newFixedThreadPool(5);

        Future<Boolean> submit = executor.submit(() -> {
            System.out.printf("thread pools, override call. thread: %s \n", Thread.currentThread());
            return true;
        });
        System.out.println(submit.get());


        // CompletableFuture
        // 创建异步执行任务:
        CompletableFuture.supplyAsync(ThreadTest::fetchPrice, executor)
                // 如果执行成功
                .thenAccept(result -> {
                    System.out.printf("price: %f, thread: %s \n", result, Thread.currentThread());
                })
                // 如果执行异常
                .exceptionally(e -> {
                    e.printStackTrace();
                    System.out.printf("exceptionally thread: %s \n", Thread.currentThread());
                    return null;
                });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);

        System.out.println(Thread.currentThread());
    }

    static Double fetchPrice() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random() * 20;
    }
}
class Thread1 extends Thread {

    @Override
    public void run() {
        System.out.printf("extends Thread, override run. thread: %s \n", Thread.currentThread());
    }
}