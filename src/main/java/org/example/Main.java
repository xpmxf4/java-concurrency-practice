package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
    public static void main(String[] args) {

        ReentrantReadWriteLock.ReadLock lock = new ReentrantReadWriteLock().readLock();

        Condition condition = lock.newCondition();
        condition.signal();


    }
}