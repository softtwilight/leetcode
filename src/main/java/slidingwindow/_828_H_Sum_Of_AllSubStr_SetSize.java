package slidingwindow;

/**
 * https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/
 *
 * Author:   softtwilight
 * Date:     2020/05/14 23:07
 */
public class _828_H_Sum_Of_AllSubStr_SetSize {
    private static final _828_H_Sum_Of_AllSubStr_SetSize instance = new _828_H_Sum_Of_AllSubStr_SetSize();

    public static void main(String[] args) {
        System.out.println(instance.uniqueLetterString("ABCA"));
    }


    /**
     * 写了一个暴力的版本，也没有那么暴力O（n ^ 2), 超时了
     */
    public int uniqueLetterString(String s) {

        int result = 0;
        char[] arr = s.toCharArray();
        for (int windowSize = 1; windowSize <= s.length(); windowSize++) {
            int[] window = new int[26];
            int lo = 0, hi = 0;

            int unique = 0;
            while (hi < windowSize) {
                window[arr[hi] - 'A']++;
                if (window[arr[hi] - 'A'] == 1) {
                    unique++;
                } else if (window[arr[hi] - 'A'] == 2) {
                    unique--;
                }
                hi++;
            }
            result += unique;
            while (hi < arr.length) {
                window[arr[lo] - 'A']--;
                if (window[arr[lo] - 'A'] == 1) {
                    unique++;
                }
                if (window[arr[lo] - 'A'] == 0) {
                    unique--;
                }
                lo++;
                window[arr[hi] - 'A']++;
                if (window[arr[hi] - 'A'] == 1) {
                    unique++;
                }
                if (window[arr[hi] - 'A'] == 2) {
                    unique--;
                }
                hi++;
                result+= unique;
            }

        }
        return result;

    }
}
