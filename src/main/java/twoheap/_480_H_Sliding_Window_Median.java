package twoheap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/sliding-window-median/
 *
 * Author:   softtwilight
 * Date:     2020/06/12 22:10
 */
public class _480_H_Sliding_Window_Median {
    private static final _480_H_Sliding_Window_Median instance = new _480_H_Sliding_Window_Median();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 139 ms	41.8 MB
     *
     * pority queue 的 remove 是liner的。。。
     * 思路同295
     */
    public double[] medianSlidingWindow(int[] nums, int k) {

        PriorityQueue<Integer> asc = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> desc = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            asc.offer(nums[i]);
        }
        for (int i = 0; i < k / 2 ; i++) {
            desc.offer(asc.poll());
        }
        int len = nums.length - k + 1;
        double[] result = new double[len];
        boolean isEven = k % 2 == 0;
        for (int i = 0; i < len; i++) {
            Integer leftMax = asc.peek();
            Integer rightMin = desc.peek();
            if (isEven) {
                result[i] = leftMax / 2.0 + rightMin / 2.0;
            } else {
                result[i] = leftMax;
            }

            if (k + i == nums.length) continue;
            if (asc.remove(nums[i])) {
                desc.offer(nums[k + i]);
                asc.offer(desc.poll());
            } else {
                desc.remove(nums[i]);
                asc.offer(nums[k + i]);
                desc.offer(asc.poll());
            }
        }
        return result;
    }

    public double[] medianSlidingWindow2(int[] nums, int k) {
        Comparator<Integer> comparator = (a, b) -> nums[a] == nums[b] ?
                Integer.compare(nums[a], nums[b]) : a - b;
        TreeSet<Integer> left = new TreeSet<>(comparator.reversed());
        TreeSet<Integer> right = new TreeSet<>(comparator);

        int len = nums.length - k + 1;
        double[] result = new double[len];
        return result;
    }
}
