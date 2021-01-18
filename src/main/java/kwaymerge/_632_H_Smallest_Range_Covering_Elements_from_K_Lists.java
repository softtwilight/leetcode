package kwaymerge;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Author:   softtwilight
 * Date:     2021/01/18 21:10
 *
 * https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
 */
public class _632_H_Smallest_Range_Covering_Elements_from_K_Lists {
    private static final _632_H_Smallest_Range_Covering_Elements_from_K_Lists instance = new _632_H_Smallest_Range_Covering_Elements_from_K_Lists();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * the idea is like the k way merge, we just choose the minimum number to substitute
     * (minimum and max define the range, so we can choose min or max to improve the range)
     */
    public int[] smallestRange(List<List<Integer>> nums) {


        int len = Integer.MAX_VALUE, lo = 0, hi = 0, curMax = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < nums.size(); i++) {
            int num = nums.get(i).get(0);
            curMax = Math.max(curMax, num);
            // num[0] is the value, nums[1] is the list index of list, nums[2] is value index of list
            queue.offer(new int[]{num, i, 0});
        }

        while (!queue.isEmpty()) {
            int[] min = queue.poll();
            int range = curMax - min[0];
            if (range < len) {
                len = range;
                hi = curMax;
                lo = min[0];
            }
            List<Integer> ne = nums.get(min[1]);
            if (min[2] + 1 >= ne.size()) {
                break;
            }
            int nextValue = ne.get(min[2] + 1);
            curMax = Math.max(curMax, nextValue);
            queue.offer(new int[]{nextValue, min[1], min[2] + 1});

        }
        return new int[]{lo, hi};
    }
}
