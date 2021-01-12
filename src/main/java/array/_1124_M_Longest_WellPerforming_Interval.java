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
        int[] hours = {-9,6,6};
        System.out.println(instance.longestWPI(hours));
    }

    /**
     *
     */
    public int longestWPI(int[] hours) {
        int result = 0;
        int[] prefixSum = new int[hours.length + 1];
        Map<Integer, Integer> indexCache = new HashMap<>();
        indexCache.put(0, 0);
        for (int i = 0; i < hours.length; i++) {
            if (hours[i] > 8) {
                prefixSum[i + 1] = prefixSum[i] + 1;
            } else {
                prefixSum[i + 1] = prefixSum[i] - 1;
            }
            if (!indexCache.containsKey(prefixSum[i + 1])) {
                indexCache.put(prefixSum[i + 1], i + 1);
            }
        }
//        System.out.println(Arrays.toString(prefixSum));
        for (int i = 0; i < prefixSum.length; i++) {
            if (prefixSum[i] > 0) {
                result = Math.max(result, i);
            } else {
                if (indexCache.containsKey(prefixSum[i] - 1)) {
                    int lo = indexCache.get(prefixSum[i] - 1);
                    result = Math.max(result, i - lo);
                }
            }
        }
        return result;
    }
}
