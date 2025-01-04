package org.example.chapter10.exam03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableExample {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Runnable runnableTask = () -> {
            System.out.println("callable 작업 수행중 ...");
            System.out.println("callable 작업 수행중 ...");
        };

        executorService.submit(runnableTask); // 결과 값을 반환받을 수 없다.

        executorService.shutdown();
    }
}
