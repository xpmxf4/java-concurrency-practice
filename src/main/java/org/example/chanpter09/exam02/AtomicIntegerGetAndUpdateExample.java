package org.example.chanpter09.exam02;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerGetAndUpdateExample {

    private static AtomicInteger accountBalance = new AtomicInteger(1200);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                int withdrawalAmount = 500; // 출금액
                int updatedBalance = accountBalance.getAndUpdate(balance -> {

                    if (balance >= withdrawalAmount) {
                        return balance - withdrawalAmount; // 출금 성공
                    } else {
                        return balance; // 잔고 부족
                    }
                });

                if (updatedBalance < 0) {
                    System.out.println(Thread.currentThread().getName() + " : 잔고 부족으로 출금 실패");
                } else {
                    System.out.println(Thread.currentThread().getName() + " 출금 후 잔고 : " + updatedBalance);
                }
            }).start();
        }
    }
}
