package org.example.chapter10.exam06;

import java.util.concurrent.*;

public class SubmitCallableExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello Callable!";
            }
        });

        System.out.println("future.get() = " + future.get());

        executorService.shutdown();
    }
}
