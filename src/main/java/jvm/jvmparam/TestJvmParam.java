package jvm.jvmparam;

public class TestJvmParam {

    public static void main(String[] args) {

        System.out.println("*****hello****");


        byte[] bytes = new byte[6 * 1024 * 1024];

        /*System.gc();*/

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



//        try {
//            System.out.println("begin...");
//            return;
//        }catch (Exception e){
//            System.out.println("exception");
//        }finally {
//            System.out.println("finally");
//        }

    }
}
