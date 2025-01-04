package org.example.chapter10.exam04;

import java.util.concurrent.*;

public class FutureCallbackExample {

    interface Callback {
        void onComplete(int result);
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> callableTask = () -> {
            System.out.println("Callable 작업 수행중...");
            System.out.println("Callable 작업 수행중...");
            Thread.sleep(1000);

            return 42;
        };

        Future<Integer> future = executorService.submit(callableTask);
        System.out.println("비동기 작업 시작");

        registerCallback(future, result -> {
            System.out.println("최종 작업");
            System.out.println("비동기 작업 결과 : " + result);
        });

        executorService.shutdown();
    }

    private static void registerCallback(Future<Integer> future, Callback callback) {
        new Thread(() -> {
            Integer result;
            try {
                result = future.get();
                System.out.println("result = " + result);
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            callback.onComplete(result);
        }).start();

    }
}
