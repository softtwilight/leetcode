package mit_6006;

/**
 * Author:   softtwilight
 * Date:     2020/11/10 21:47
 *
 * Big number multiplication
 * https://leetcode.com/problems/multiply-strings/
 */
public class _43_M_Multiply_Strings {
    private static final _43_M_Multiply_Strings instance = new _43_M_Multiply_Strings();

    public static void main(String[] args) {

        System.out.println(instance.multiply("123456789", "987654321"));
        System.out.println('1' - 1);
    }

    /**
     * non-negative integers
     *
     * 2 ^ 63 - 1 = 9.223372e+18
     *
     * 利用divide-conquer方法解的，在merge子方法的解的时候还有问题没解决。
     */
    public String multiply(String num1, String num2) {
        if (num1 == null || num1.length() == 0) return num2;
        if (num2 == null || num2.length() == 0) return num1;
        if (num1.equals("0") || num2.equals("0")) return "0";

        if (num1.length() < 9 && num2.length() < 9) {
            Long result = Long.parseLong(num1) * Long.parseLong(num2);
            return result.toString();
        }
        String big1 = num1.substring(0, num1.length() / 2);
        String low1 = num1.substring(num1.length() / 2);
        String big2 = num2.substring(0, num2.length() / 2);
        String low2 = num2.substring(num2.length() / 2);




        String X0 = multiply(big1, big2);
        String X3 = multiply(low1, low2);
        String X1 = multiply(big1, low2);
        String X2 = multiply(big2, low1);


        int i = X1.length() - 1, j = X2.length() - 1;



        StringBuilder sb1 = new StringBuilder(X1);
        int len1 = (num1.length() + 1) / 2;
        int len2 = (num2.length() + 1) / 2;
        for (int k = 0; k < len1; k++) {
            sb1.append('0');
        }
        StringBuilder sb2 = new StringBuilder(X2);
        for (int k = 0; k < len2; k++) {
            sb2.append('0');
        }
        StringBuilder sb0 = new StringBuilder(X0);
        for (int k = 0; k < len1 + len2; k++) {
            sb0.append('0');
        }
        String temp = addTwoString(X3, sb1);
        temp = addTwoString(temp, sb2);
        temp = addTwoString(temp, sb0);
        return temp;
//        return addTwoString(addTwoString(addTwoString(X3, sb1), sb2), X0);
    }

    String addTwoString(CharSequence X1, CharSequence X2) {
        StringBuilder re = new StringBuilder();
        int i = X1.length() - 1;
        int j = X2.length() - 1;
        int cur = 0;
        while (i >= 0 || j >= 0) {
            int x1 = 48;
            if (i >= 0) {
                x1 = X1.charAt(i);
                i--;
            }
            int x2 = 48;
            if (j >= 0) {
                x2 = X2.charAt(j);
                j--;
            }
            char c = (char)((x1 - 48 + cur) + x2);
            if (c > '9') {
                cur = 1;
                re.append((char)(c - 10));
            } else {
                cur = 0;
                re.append(c);
            }
        }
        if (cur == 1) {
            re.append('1');
        }
        return re.reverse().toString();
    }
}
