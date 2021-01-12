package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Author:   softtwilight
 * Date:     2021/01/12 23:08
 *
 * https://leetcode.com/problems/longest-well-performing-interval/
 */
public class _1124_M_Longest_WellPerforming_Interval {
    private static final _1124_M_Longest_WellPerforming_Interval instance = new _1124_M_Longest_WellPerforming_Interval();

    public static void main(String[] args) {
        int[] hours = {9,6,6};
        System.out.println(instance.longestWPI(hours));
    }

    /**
     * 两个循环简化为一个循环
     */
    public int longestWPI(int[] hours) {
        int result = 0;
        int stack = 0;
        Map<Integer, Integer> indexCache = new HashMap<>();
        for (int i = 0; i < hours.length; i++) {
            stack += hours[i] > 8 ? 1 : -1;
            if (stack > 0) {
                result = i + 1;
            } else {
                indexCache.putIfAbsent(stack, i);
                if (indexCache.containsKey(stack - 1)) {
                    result = Math.max(result, i - indexCache.get(stack - 1));
                }
            }


        }
        return result;
    }
}
