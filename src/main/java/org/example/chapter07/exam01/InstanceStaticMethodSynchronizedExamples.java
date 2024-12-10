package org.example.chapter07.exam01;

public class InstanceStaticMethodSynchronizedExamples {

    private int count = 0;

    public void increment() {
        count++;
        System.out.println(Thread.currentThread().getName() + "가 증가 시켰습니다. 현재 값 : " + count);
    }

    public synchronized void decrement() {
        count--;
        System.out.println(Thread.currentThread().getName() + "가 감소 시켰습니다. 현재 값 : " + count);
    }

    public static void main(String[] args) {

    }
}
