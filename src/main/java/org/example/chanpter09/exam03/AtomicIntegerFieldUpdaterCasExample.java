package org.example.chanpter09.exam03;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterCasExample {
    private static AtomicIntegerFieldUpdater<MyClass> fieldUpdater;

    public static class MyClass {
        volatile int counter;

        public int getCounter() {
            return counter;
        }
    }

    private static int NUM_THREADS = 3;

    public static void main(String[] args) {
        MyClass instance = new MyClass();
        fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(MyClass.class, "counter");

        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    int expectedValue;
                    int newValue;
                    do {
                        expectedValue = fieldUpdater.get(instance);
                        newValue = expectedValue + 1;
                    } while (!fieldUpdater.compareAndSet(instance, expectedValue, newValue));
                    System.out.println(Thread.currentThread().getName() + " : " + expectedValue + " -> " + newValue);
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Final value: " + fieldUpdater.get(instance));
    }
}
