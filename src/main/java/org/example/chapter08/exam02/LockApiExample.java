package org.example.chapter08.exam02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockApiExample {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            lock.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        });

        Thread thread2 = new Thread(() -> {
            lock.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        });

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // Lock 에 대한 정보 출력
        System.out.println("Hold Count : " + lock.getHoldCount());
        System.out.println("Is Held By Current Thread : " + lock.isHeldByCurrentThread());
        System.out.println("Has Queued Threads : " + lock.hasQueuedThreads());
        System.out.println("Has Queued Thread1 : " + lock.hasQueuedThread(thread1));
        System.out.println("Has Queued Thread2 : " + lock.hasQueuedThread(thread2));
        System.out.println("Queue Length : " + lock.getQueueLength());
        System.out.println("Fairness : " + lock.isFair());
    }
}
