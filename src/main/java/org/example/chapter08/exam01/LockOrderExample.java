package org.example.chapter08.exam01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockOrderExample {

    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            lock1.lock();
            try {
                System.out.println("lock1을 획득했습니다.");
                lock2.lock();
                try {
                    System.out.println("lock2를 획득했습니다.");
                } finally {
                    lock1.unlock();
                    System.out.println("lock1을 해제했습니다.");
                }
            } finally {
                lock2.unlock();
                System.out.println("lock2를 해제했습니다.");
            }
        }).start();

    }
}
