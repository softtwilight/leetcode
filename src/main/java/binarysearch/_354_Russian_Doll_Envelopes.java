package binarysearch;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/**
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h).
 * One envelope can fit into another if and only if both the width and height
 * of one envelope is greater than the width and height of the other envelope.
 *
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 *
 * Note:
 * Rotation is not allowed.
 *
 * Example:
 *
 * Input: [[5,4],[6,4],[6,7],[2,3]]
 * Output: 3
 * Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 *
 * Author:   softtwilight
 * Date:     2020/05/05 23:18
 */
public class _354_Russian_Doll_Envelopes {

    public static void main(String[] args) {
        int[][] input = {{5,4},{6,4},{6,7},{2,3}};
        System.out.println(maxEnvelopes(input));
    }

    /**
     * 没有思路啊
     */
    public static int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;
        if (envelopes.length == 1) return 1;
        Arrays.sort(envelopes, Comparator.comparing(env -> env[0]));

        int dp[] = new int[envelopes.length];
        int len = 0;
        for(int[] envelope : envelopes){
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
            if(index < 0)
                index = -(index + 1);
            dp[index] = envelope[1];
            if(index == len)
                len++;
        }
        return len;
    }

}
