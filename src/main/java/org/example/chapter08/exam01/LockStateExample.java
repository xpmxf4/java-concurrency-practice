package org.example.chapter08.exam01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockStateExample {
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("lock을 1번 획득했습니다.");
                lock.lock();
                try {
                    System.out.println("lock을 2번 획득했습니다.");
                } finally {
                    lock.unlock();

                }
            } finally {
                System.out.println("lock을 1번 해제했습니다.");
                lock.unlock();
            }
        });
    }
}

