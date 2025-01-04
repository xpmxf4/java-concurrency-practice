package org.example.chapter10.exam03;

import java.util.concurrent.*;

public class CallableExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> callableTask = () -> {
            System.out.println("callable 작업 수행중 ...");
            System.out.println("callable 작업 수행중 ...");

            return 42;
        };

        Future<Integer> future = executorService.submit(callableTask);
        System.out.println("future.get() = " + future.get());

        executorService.shutdown();
    }
}
