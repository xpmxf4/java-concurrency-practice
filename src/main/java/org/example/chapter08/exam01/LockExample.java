package org.example.chapter08.exam01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    private int count = 0;

    private final Lock lock = new ReentrantLock(); // NonFairSync

    public void increment() {
        lock.lock(); // synchronized(this)와 같은 역할
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        LockExample lockExample = new LockExample();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                lockExample.increment();
            }
        }, "Thread1");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                lockExample.increment();
            }
        }, "Thread2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("최종 값 : " + lockExample.getCount());
    }
}
