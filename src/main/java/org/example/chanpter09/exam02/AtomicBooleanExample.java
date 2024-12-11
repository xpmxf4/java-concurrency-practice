package org.example.chanpter09.exam02;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanExample {

    private static AtomicBoolean flag = new AtomicBoolean(false);

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                while (!flag.compareAndSet(false, true)) {
                    System.out.println("스레드 1이 바쁜 대기중..." + flag.get());
                }
                // critical section
                System.out.println("thread-1 이 임계 영역 수행 중...");

                // 다시 flag 상태 원복
                flag.set(false);

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Thread-1");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                while (!flag.compareAndSet(false, true)) {
                    System.out.println("스레드 2이 바쁜 대기중..." + flag.get());
                }
                // critical section
                System.out.println("thread-2 이 임계 영역 수행 중...");

                // 다시 flag 상태 원복
                flag.set(false);

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Thread-2");

        thread1.start();
        thread2.start();
    }
}
