package org.example.chapter10.exam11;

import java.util.concurrent.*;

public class PrestartThreadsExample {
    public static void main(String[] args) {

        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 0L;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();

        int taskNum = 9;

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                workQueue
        );

        executor.prestartCoreThread();
        executor.prestartCoreThread();

//        executor.prestartAllCoreThreads();

        for (int i = 0; i < taskNum; i++) {
            final int taskId = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            executor.execute(() -> System.out.println(Thread.currentThread().getName()+ " 가 task "+ taskId + "를 실행하고 있습니다."));
        }

        executor.shutdown();

    }
}
