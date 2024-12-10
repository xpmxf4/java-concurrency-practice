package org.example.chapter07.exam01;

public class InstanceMethodSynchronizedExamples2 {

    private int count = 0;

    public void increment() {
        count++;
        System.out.println(Thread.currentThread().getName() + "가 증가 시켰습니다. 현재 값 : " + count);
    }

    public synchronized void decrement() {
        count--;
        System.out.println(Thread.currentThread().getName() + "가 감소 시켰습니다. 현재 값 : " + count);
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {

        InstanceMethodSynchronizedExamples2 counter1 = new InstanceMethodSynchronizedExamples2();
        InstanceMethodSynchronizedExamples2 counter2 = new InstanceMethodSynchronizedExamples2();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter1.increment();
                counter2.decrement();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter2.increment();
                counter1.decrement();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("counter1 최종 값 : " + counter1.getCount());
        System.out.println("counter2 최종 값 : " + counter2.getCount());
    }
}
