package org.example.chapter10.exam11;

import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorHook extends ThreadPoolExecutor {

    public ThreadPoolExecutorHook(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, LinkedBlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void terminated() {
        System.out.println("스레드 풀이 종료되었습니다.");
        super.terminated();
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        if (Objects.isNull(t)) { // 예외
            System.out.println("작업이 성공적으로 완료했습니다.");
        } else {
            System.out.println("작업이 " + t.getMessage() + " 예외가 발생했습니다.");
        }
        super.afterExecute(r, t);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println(t.getName() + "가 작업을 실행하려고 합니다.");
        super.beforeExecute(t, r);
    }

    public static void main(String[] args) {

        int corePoolSize = 2;
        int maxPoolSize = 2;
        long keepAliveTime = 0L;
        int workQueueCapacity = 2;

        // 커스텀 스레드 풀 생성
        ThreadPoolExecutorHook executor = new ThreadPoolExecutorHook(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingQueue<>(workQueueCapacity));

        for (int i = 1; i < 5; i++) {
            final int taskId = i;

            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "가 테스크 " + taskId + "를 실행하고 있습니다.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
    }
}
