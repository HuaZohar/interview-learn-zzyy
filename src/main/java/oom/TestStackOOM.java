package oom;

public class TestStackOOM {

    public static void main(String[] args) {
        stackOOM();
    }

    private static void stackOOM() {
        stackOOM(); //Exception in thread "main" java.lang.StackOverflowError
    }
}
