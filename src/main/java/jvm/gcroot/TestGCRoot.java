package jvm.gcroot;

/**
 * 虚拟机栈（栈帧中的局部变量区，也叫做局部变量表）中引用的对象，如下 t1
 *
 * 方法区中的类静态属性引用的对象 如下 t2
 *
 * 方法区中常量引用的对象 如下 t3
 *
 * 本地方法栈中JNI（Navite方法）引用的对象
 */
public class TestGCRoot {

    //方法区中的类静态属性引用的对象
    private static TestGCRoot t2;

    //方法区中常量引用的对象
    private static final TestGCRoot t3 = new TestGCRoot();

    public static void m1(){
        //t1 为虚拟机栈引用的对象
        TestGCRoot t1 = new TestGCRoot();
        System.gc();
        System.out.println("第一次GC完成");
    }

    public static void main(String[] args) {
        m1();
    }
}
