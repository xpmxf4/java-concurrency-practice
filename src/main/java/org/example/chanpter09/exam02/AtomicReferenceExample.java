package org.example.chanpter09.exam02;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceExample {

    public static void main(String[] args) {

        User user1 = new User("Alice", 25);
        User user2 = new User("Bob", 30);

        AtomicReference<User> atomicReference = new AtomicReference<>(user1);

        Thread thread1 = new Thread(() -> {
            User updateUser = new User("Carol", 40);

            boolean success = atomicReference.compareAndSet(user1, updateUser);
            if (success) {
                System.out.println("스레드 1 이 " + updateUser + " 로 변경되었습니다.");
            } else {
                System.out.println("스레드 1 이 " + updateUser + " 로 변경 실패했습니다.");
            }
        });

        Thread thread2 = new Thread(() -> {
            User updateUser = new User("Kevin", 77);

            boolean success = atomicReference.compareAndSet(user1, updateUser);
            if (success) {
                System.out.println("스레드 2 이 " + updateUser + " 로 변경 했습니다.");
            } else {
                System.out.println("스레드 2 이 " + updateUser + " 로 변경 실패했습니다.");
            }
        });

        thread2.start();
        thread1.start();

//        thread1.join();
//        thread2.join();

        System.out.println("Final User : " + atomicReference.get());
    }
}

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return
                "{name='" + name + '\'' +
                        ", age=" + age +
                        '}';
    }
}