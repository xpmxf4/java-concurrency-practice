package org.example.chapter07.exam01;

public class InstanceMethodSynchronizedExamples {

    private int count = 0;

    public synchronized void increment() {
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

        InstanceMethodSynchronizedExamples count = new InstanceMethodSynchronizedExamples();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                count.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                count.decrement();
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

        System.out.println("최종 값 : " + count.getCount());
    }
}
