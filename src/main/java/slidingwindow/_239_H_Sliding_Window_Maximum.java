package slidingwindow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * Given an array nums, there is a sliding window of size k which is moving from the very
 * left of the array to the very right. You can only see the k numbers in the window.
 * Each time the sliding window moves right by one position. Return the max sliding window.
 *
 * Follow up:
 * Could you solve it in linear time?
 *
 * Example:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 *
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 *
 * Author:   softtwilight
 * Date:     2020/05/12 23:00
 */
public class _239_H_Sliding_Window_Maximum {
    private static final _239_H_Sliding_Window_Maximum instance = new _239_H_Sliding_Window_Maximum();

    public static void main(String[] args) {
//        int[] input = {1,3,-1,-3,5,3,6,7};
//        int[] input = {1,2,3,4,5,6,7,8};
//        int[] input = {7,6,5,4,3,2,1};
        int[] input = {-7,-8,7,5,7,1,6,0};
        int k = 4;
        int[] re = instance.maxSlidingWindow(input, k);
        System.out.println(Arrays.toString(re));

    }





    /**
     * 11 ms  +	50.9 MB
     * 利用deque来记录大小顺序
     * 对新入 queue的数，清除掉queue里比他小的数， 然后入queue
     * 这个queue保持单调递减（包含 = ，第一次的提交的bug的error就出现在这里
     * 因为出queue的时候，是判断nums[lo]是否等于queue.peek， 所以如果不包含相等， 就可能把其后的最大值移出去）
     * queue头就是最大值，然后用来两个指针维护window就行了
     *
     * worse复杂度是O（n * 2）
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        int[] result = new int[nums.length - k + 1];
        int lo = 0, hi = 0;
        Deque<Integer> window = new ArrayDeque<>();
        while (hi < nums.length) {
            while (!window.isEmpty() && nums[hi] > window.peekLast()) {
                window.pollLast();
            }
            if (window.isEmpty() || nums[hi] <= window.peekLast()) {
                window.add(nums[hi]);
            }
            if (hi - lo + 1 == k) {
                result[lo] = window.peekFirst();
                if (nums[lo] == window.peekFirst()) {
                    window.pollFirst();
                }
                lo++;
            }
            hi++;
        }
        return result;
    }


    /**
     * 这是看提交前最快的答案，
     * 思路其实更简单。
     *
     * 只记录最大值，如果最大值被移出window，那就再遍历一次k，然后最大值
     * （这个对单调递减的input 表现最差， 每次都需要遍历k ，worse复杂度为O（N * k））
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return new int[]{};
        }
        if (k > nums.length) {
            k = nums.length;
        }
        int[] result = new int[nums.length - k + 1];
        int maxi = 0;
        int right = 1;
        for (; right < k; right++) {
            if (nums[right] >= nums[maxi]) {
                maxi = right;
            }
        }
        result[0] = nums[maxi];
        int ri = 1;
        while (right < nums.length) {
            if (nums[right] >= nums[maxi]) {
                // if new item is max
                maxi = right;
            } else if (maxi == right - k) {
                // max slid out, find new max in current window
                maxi++;
                for (int i = maxi + 1; i <= right; i++) {
                    if (nums[i] >= nums[maxi]) {
                        maxi = i;
                    }
                }
            }
            result[ri++] = nums[maxi];
            right++;
        }
        return result;
    }
}
