package array;

import java.util.*;

/**
 * Author:   softtwilight
 * Date:     2021/01/12 23:08
 *
 * https://leetcode.com/problems/longest-well-performing-interval/
 */
public class _1124_M_Longest_WellPerforming_Interval {
    private static final _1124_M_Longest_WellPerforming_Interval instance = new _1124_M_Longest_WellPerforming_Interval();

    public static void main(String[] args) {
        int[] hours = {9,9,6,0,6,6,9};
        System.out.println(instance.longestWPI(hours));
    }


    /**
     * a more general approach to solve this kind of problem.
     *
     * input: arrary arr in which elements are arbitrary integers.
     * output: length of a longest subarray arr[i, j) with sum(arr[i], ... , arr[j-1]) >= K.
     *
     */
    public int longestWPI(int[] hours) {
        int[] prefixSum = new int[hours.length + 1];
        for (int i = 1; i <= hours.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + (hours[i - 1] > 8 ? 1 : - 1);
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < prefixSum.length; i++) {
            if (stack.isEmpty() || prefixSum[stack.peek()] > prefixSum[i]) {
                stack.push(i);
            }
        }

        int result = 0;
        System.out.println(Arrays.toString(prefixSum));
        System.out.println(stack);
        for (int i = prefixSum.length - 1; i >= 0 ; i--) {
            while (!stack.isEmpty() && prefixSum[i] > prefixSum[stack.peek()]) {
                result = Math.max(result, i - stack.pop());
            }
        }

        return result;

    }

    /**
     * 两个循环简化为一个循环
     */
    public int longestWPI2(int[] hours) {
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
