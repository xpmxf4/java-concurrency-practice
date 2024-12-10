package org.example.chapter08.exam02;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockWithTimeoutExample {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            try {
                if (lock.tryLock(2, TimeUnit.SECONDS)) {
                    System.out.println("thread1이 lock을 획득했습니다.");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        lock.unlock();
                        System.out.println("thread1이 lock을 해제했습니다.");
                    }
                } else {
                    System.out.println("thread1이 lock을 획득하지 못했습니다.");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread1");

        Thread thread2 = new Thread(() -> {
            try {
                if (lock.tryLock(4, TimeUnit.SECONDS)) {
                    System.out.println("thread2이 lock을 획득했습니다.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        lock.unlock();
                        System.out.println("thread2이 lock을 해제했습니다.");
                    }
                } else {
                    System.out.println("thread2이 lock을 획득하지 못했습니다.");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread2");

        thread1.start();
        thread2.start();
    }
}
