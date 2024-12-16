package org.example.chanpter09.exam04;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

    private static int[] parallelSum = new int[2];


    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int numThreads = 2;

        CyclicBarrier barrier = new CyclicBarrier(numThreads, new BarrierAction(parallelSum));

        for (int i = 0; i < numThreads; i++) {
            new Thread(new Worker(i, numbers, barrier, parallelSum)).start();
        }
    }
}

class BarrierAction implements Runnable {

    private int[] parallelSum;

    public BarrierAction(int[] parallelSum) {
        this.parallelSum = parallelSum;
    }

    @Override
    public void run() {
        int finalSum = 0;

        for (int sum : parallelSum) {
            finalSum += sum;
        }

        System.out.println("최종 합계 : " + finalSum);
        System.out.println();
    }
}

class Worker implements Runnable {

    private final int id;
    private final int[] numbers;
    private final CyclicBarrier barrier;
    private final int[] parallelSum;

    public Worker(int id, int[] numbers, CyclicBarrier barrier, int[] parallelSum) {
        this.id = id;
        this.numbers = numbers;
        this.barrier = barrier;
        this.parallelSum = parallelSum;
    }

    @Override
    public void run() {
        int start = id * (numbers.length / 2);
        int end = (id + 1) * (numbers.length / 2);

        int sum = 0;

        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }

        parallelSum[id] = sum;

        try {
            barrier.await();
            System.out.println("대기에서 풀려났습니다.");

            barrier.await();    // CyclicBarrier 는 CountDownLatch 와는 다르게 재사용이 가능하다!
            System.out.println("또 따른 대기에서 풀려났습니다.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}