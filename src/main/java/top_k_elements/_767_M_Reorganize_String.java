package top_k_elements;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/reorganize-string/
 *
 * Author:   softtwilight
 * Date:     2020/06/19 22:41
 */
public class _767_M_Reorganize_String {
    private static final _767_M_Reorganize_String instance = new _767_M_Reorganize_String();

    public static void main(String[] args) {
        System.out.println(instance.reorganizeString("abccc"));
    }

    /**
     * 1 ms	37.6 MB
     *
     * 前面都很简单啦，主要输出结果。
     * 我们总是采用不等于上一个， 但是是所剩下的字符里最多的一个， 交替
     *
     *
     */
    public String reorganizeString(String S) {

        int[] memo = new int[26];
        for (char c : S.toCharArray()) {
            memo[c - 'a']++;
        }
        int max = 0;
        for (int i = 0; i < memo.length; i++) {
            max = Math.max(max, memo[i]);
        }
        if (max > (S.length() + 1) / 2) {
            return "";
        }
        return scatter2(memo, S.length());
    }

    // 这种方法必须优先排最多的字母， 不然abccc这样的情况就不行，偶数是可以的
    // 先排列坐标为偶数的， 然后排满之后排坐标是基数的。 因为只有max 有可能大于length的二分之1，（但是不大于1/2 + 1）
    // 故都会不与自己相遇
    private String scatter2(int[] memo, int len) {
        char[] result = new char[len];
        int index = 0;
        int max = 0;
        for (int i = 0; i < memo.length; i++) {
            if (memo[i] > memo[max]) {
                max = i;
            }
        }
        while (memo[max] > 0) {
            result[index] = (char) (max + 'a');
            index += 2;
            memo[max]--;
        }
        for (int i = 0; i < memo.length; i++) {
            while (memo[i] > 0) {
                if (index >= len) {
                    index = 1;
                }
                result[index] = (char) (i + 'a');
                index += 2;
                memo[i]--;
            }
        }
        return new String(result);
    }

    private String scatter(int[] memo, int len) {
        StringBuilder result = new StringBuilder();
        int last = -1;
        for (int i = 0; i < len; i++) {
            int m = -1;
            int biggest = 0;
            for (int j = 0; j < memo.length; j++) {
                if (memo[j] > biggest && j != last) {
                    m = j;
                    biggest = memo[j];
                }
            }
            last = m;
            memo[m]--;
            result.append((char)(m + 'a'));
        }
        return result.toString();
    }
}
