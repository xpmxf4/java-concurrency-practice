package org.example.chapter10.exam06;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SubmitRunnableExample {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hello Runnable!");
        }, "RESULT");

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Hello Runnable without result!");
            }
        });

        executorService.shutdown();
    }
}
