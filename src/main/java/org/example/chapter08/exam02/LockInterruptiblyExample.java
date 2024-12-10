package org.example.chapter08.exam02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyExample {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                System.out.println("thread1이 lock을 획득했습니다.");
                lock.unlock();
                System.out.println("thread1이 lock을 해제했습니다.");
            } catch (InterruptedException e) {
                System.out.println("thread1이 interrupted 됐습니다.");
            }
        }, "Thread1");

        Thread thread2 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                try {
                    Thread.sleep(1000);
                    System.out.println("thread2이 lock을 획득했습니다.");
                } finally {
                    lock.unlock();
                    System.out.println("thread2이 lock을 해제했습니다.");
                }
            } catch (InterruptedException e) {
                System.out.println("thread2이 interrupted 됐습니다.");
            }
        }, "Thread2");

        thread1.start();
        thread2.start();
        thread1.interrupt();
        thread2.interrupt();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
