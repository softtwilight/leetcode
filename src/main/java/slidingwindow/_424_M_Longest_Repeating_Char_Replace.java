package slidingwindow;

/**
 * https://leetcode.com/problems/longest-repeating-character-replacement/
 * Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string.
 *
 *  In one operation, you can choose any character of the string and change it to any other uppercase English character.
 *
 *  Find the length of the longest sub-string containing all repeating letters you can get after performing the above operations.
 *
 *  Note:
 *  Both the string's length and k will not exceed 104.
 *
 *
 * Author:   softtwilight
 * Date:     2020/05/13 22:13
 */
public class _424_M_Longest_Repeating_Char_Replace {
    private static final _424_M_Longest_Repeating_Char_Replace instance = new _424_M_Longest_Repeating_Char_Replace();

    public static void main(String[] args) {
        System.out.println('B' - 'A');

        String s = "AAAACABA", s2 = "ABAB";
        int k = 1, k2 = 2;
        System.out.println(instance.characterReplacement(s, k));
        System.out.println(instance.characterReplacement(s2, k2));
    }


    /**
     * 3 ms	39.7 MB
     *
     * 这里转化题意，就是窗口之中不是最多的字符的总素 <= k
     * 我们可以记录最大值的个数，然后用长度减去最大值个数来与k比较，
     * （这里其实我多定义了一个变量allCount， 长度可以用hi，lo得到）
     *
     * 如果大于k我们缩小窗口，这里又有一个tricky的地方， 因为如果窗口除去的是最多个数的字符
     * 那么maxCount会改变，变成 maxCount - 1 或者 maxCount（之前有两个以上字符是maxCount个）
     * 所以我采用的办法办理memo数组，重新找最大值。
     *
     * 这是我跟最快的答案的区别点，他们会忽略maxCount的变化。
     *
     * 假设他们是正确的
     * 那我们可以倒推， maxCount - 1 和 maxCount 是不影响结果的。
     * 怎么验证呢？ 也就是说maxCount -> maxCount - 1 没有影响。
     *
     * 我们假设移去的是'A'，为唯一最多字符。
     * 我们可以发现，下一次循环中，如果maxCount不会被更新，那么新加入一个元素，就会移出一个元素
     * 也就是意味着如果maxCount不更新， result的值永远也不会被更新。
     * 实际上上result = maxCount + max(被用到的替换机会） <= maxCount + k
     * 当allCount - maxCount > k 时，也就是所有的k机会都用到了，这时候要更新result， 只能更新maxCount。
     *
     * 所以这里maxCount 不准确可能变为maxCount - 1 是不影响结果的。
     * 改了之后运行时间还是一样的
     */
    public int characterReplacement(String s, int k) {

        if (s == null || s.length() == 0) return 0;
        if (k >= s.length() - 1) return s.length();
        k = k < 0 ? 0 : k;


        int[] memo = new int[26];
        char[] cs = s.toCharArray();
        int lo = 0, hi = 0, allCount = 0, maxCount = 0;
        int result = 0;
        while (hi < s.length()) {
            char hiChar = cs[hi];
            memo[hiChar - 'A']++;
            allCount++;
            if (memo[hiChar - 'A'] > maxCount) {
                maxCount = memo[hiChar - 'A'];
            }
            while (allCount - maxCount > k) {
                allCount--;
                memo[cs[lo] - 'A']--;

                // v2关于注释掉这段的解释参考题目
//                if (memo[cs[lo] - 'A'] == maxCount - 1) {
//                    for (int n : memo) {
//                        maxCount = Math.max(n, maxCount);
//                    }
//                }
                lo++;
            }
            result = Math.max(result, hi - lo + 1);
            hi++;
        }
        return result;
    }
}
