package org.example.chanpter09.exam02;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

public class AtomicIntegerApiExample {

    public static void main(String[] args) {
        AtomicInteger atomicInt = new AtomicInteger(10);
        int currValue = atomicInt.get();
        System.out.println("currValue = " + currValue); // 10

        atomicInt.set(20);
        System.out.println("New value " + atomicInt.get()); // 20

        int previousValue = atomicInt.getAndSet(30);
        System.out.println("Previous Value : " + previousValue); // 20

        int newValue = atomicInt.incrementAndGet();
        System.out.println("New value after increment : " + newValue); // 31

        boolean updated = atomicInt.compareAndSet(31, 40);
        System.out.println("updated? " + updated); // true
        System.out.println("new value : " + atomicInt.get()); // 40

        IntUnaryOperator addFive = value -> value + 5;
        int prevValue = atomicInt.getAndUpdate(addFive); // 40
        System.out.println("Previous Value" + prevValue); // 40
        System.out.println("Next Value " + atomicInt.get()); // 45

    }
}
