package org.example.chapter10.exam10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FixedPoolExample {
    private static final int NUM_OF_THREADS = 5;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_THREADS);
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
}
