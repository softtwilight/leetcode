package slidingwindow;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/permutation-in-string/
 * Given two strings s1 and s2, write a function to return true if s2 contains thepermutation of s1.
 * In other words, one of the first string's permutations is the substring of the second string.
 * Example 1:
 *
 * Input: s1 = "ab" s2 = "eidbaooo"
 * Output: True
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 *
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 *
 *
 * Note:
 *
 * The input strings only contain lower case letters.
 * The length of both given strings is in range [1, 10,000].
 *
 * Author:   softtwilight
 * Date:     2020/05/14 22:18
 */
public class _567_M_Permutation_In_String {
    private static final _567_M_Permutation_In_String instance = new _567_M_Permutation_In_String();

    public static void main(String[] args) {
        String s1 = "ab", s2 = "ba";
        System.out.println(instance.checkInclusion(s1, s2));

    }


    /**
     * 3 ms	+ 39.4 MB
     * 依然是滑动窗口。
     * 用第一个数组记录要match的字符，第二个记录窗口中字符的个数
     *
     * 要包含子串，意味着
     *  1.窗口的长度与s1的长度一样
     *  2.窗口从所有字符对应的个数不多于s1中的个数
     *
     * 我们用1来判断找到没有，用2来缩小窗口。
     * 在最开始的版本里，我试图判断字符是否在toMatch中，再添加到window中，
     * 如果不在，就更新lo = hi + 1；（这里的问题是需要把window清空，漏掉了这一步）
     *
     * 第二版就是如下的了。
     * 实际上这样改之后，window数组也是可以去掉的，
     * 直接在toMatch上++， --， while判断条件变为 < 0就行了。
     * 有window更好理解一点，就不改了
     */
    public boolean checkInclusion(String s1, String s2) {

        int[] toMatch = new int[26];
        for (char c : s1.toCharArray()) {
            toMatch[c - 'a']++;
        }
        int lo = 0, hi = 0;
        int[] window = new int[26];
        while (hi < s2.length()) {
            int curIndex = (int)s2.charAt(hi) - 'a';
            window[curIndex]++;
            while (window[curIndex] > toMatch[curIndex]) {
                window[s2.charAt(lo) - 'a']--;
                lo++;
            }
            if (hi - lo + 1 == s1.length()) {
                return true;
            }
            hi++;
        }
        return false;
    }
}
