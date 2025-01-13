package org.example.chapter10.exam10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedPoolWithThreadFactoryExample {

    private static final int NUM_OF_THREADS = 5;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CustomThreadFactory customThreadFactory = new CustomThreadFactory("custom");
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_THREADS, customThreadFactory);
        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < NUM_OF_THREADS; i++) {
            Future<String> future = executorService.submit(() -> {
                Thread.sleep(1000);
                return "Hello World - " + Thread.currentThread().getName();
            });
            futures.add(future);
        }

        for (int i = 0; i < NUM_OF_THREADS; i++) {
            String result = futures.get(i).get();
            System.out.println("result = " + result);
        }

        executorService.shutdown();

    }

    private static class CustomThreadFactory implements ThreadFactory {

        private static final AtomicInteger num = new AtomicInteger(1);
        private final String namePrefix;

        public CustomThreadFactory(String namePrefix) {
            this.namePrefix = namePrefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, namePrefix+num.getAndIncrement());

            if (thread.isDaemon())
                thread.setDaemon(false);
            if (thread.getPriority() != Thread.NORM_PRIORITY)
                thread.setPriority(Thread.NORM_PRIORITY);

            return thread;
        }
    }
}
