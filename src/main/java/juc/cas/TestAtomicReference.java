package juc.cas;


import java.util.concurrent.atomic.AtomicReference;

class User {
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}


/**
 * 原子引用
 */
public class TestAtomicReference {

    public static void main(String[] args) {
        User z3 = new User("z3",12);
        User l4 = new User("l4",112);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3, l4)+"\t"+atomicReference.get());
        System.out.println(atomicReference.compareAndSet(z3, l4)+"\t"+atomicReference.get());
    }
}
