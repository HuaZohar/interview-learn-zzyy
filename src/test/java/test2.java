import org.junit.Test;

public class test2 {

    @Test
    public void test1() {
        reverse(9646324351);
    }


    public int reverse(int x) {
        String s = String.valueOf(x);
        if (s.charAt(0) == '-') {
            s = s.substring(1) + "-";
        }
        StringBuffer tmp = new StringBuffer();
        for (int i = s.length() - 1; i >= 0; i--) {
            tmp.append(s.charAt(i));
        }
        int result = 0;
        try {
            result = Integer.parseInt(tmp.toString());
            if (result >= Math.pow(-2d, 23) && result <= Math.pow(2, 32)) {
                return result;
            }
        } catch (Exception e) {
            return result;
        }
        return result;
    }
}