package org.example.chanpter09.exam03;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicIntegerFieldUpdaterCasExample2 {

    private static AtomicReferenceFieldUpdater<AtomicIntegerFieldUpdaterCasExample2, String> messageUpdater
            = AtomicReferenceFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterCasExample2.class, String.class, "message");

    private volatile String message = "";

    public void doSomething() {
        if (messageUpdater.compareAndSet(this, "", "Hello World")) {
            for (int i = 0; i < 10; i++) {
                System.out.println(messageUpdater.get(this));
            }
            messageUpdater.set(this, "");
        } else {
            System.out.println("현재 스레드는 들어오지 못합니다 : " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        AtomicIntegerFieldUpdaterCasExample2 example2 = new AtomicIntegerFieldUpdaterCasExample2();
        new Thread(() -> {
            example2.doSomething();
        }).start();

        new Thread(() -> {
            example2.doSomething();
        }).start();
    }
}
