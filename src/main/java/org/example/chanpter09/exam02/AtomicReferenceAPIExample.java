package org.example.chanpter09.exam02;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;

public class AtomicReferenceAPIExample {

    public static void main(String[] args) {

        AtomicReference<String> reference = new AtomicReference<>("Initial Value");
        String currentValue = reference.get(); // Initial Value
        System.out.println("currentValue = " + currentValue);

        reference.set("New Value"); // New Value
        System.out.println("reference = " + reference.get());

        boolean success = reference.compareAndSet("New Value", "Updated Value");// Updated Value
        System.out.println("Update Successful? " + success); // true
        System.out.println("reference = " + reference.get()); // Updated Value

        String oldValue = reference.getAndSet("Final Value");
        System.out.println("oldValue = " + oldValue); // Updated Value
        System.out.println("current = " + reference.get()); // Final Value

        UnaryOperator<String> operator = oldValue2 -> oldValue2 + " is the 찐last value";
        reference.getAndUpdate(operator);
        System.out.println("reference.get() = " + reference.get());

        reference.set("a");
        reference.compareAndExchange("a", "b");
        System.out.println("reference = " + reference.get());
    }
}
