package mit_6006;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author:   softtwilight
 * Date:     2020/10/29 23:14
 *
 * https://leetcode.com/problems/maximum-gap/
 */
public class _164_HMaximum_Gap {
    private static final _164_HMaximum_Gap instance = new _164_HMaximum_Gap();

    public static void main(String[] args) {
        int[] input = {3,6,9,1,200, 2147483647};

        System.out.println(instance.maximumGap(input));
    }

    /**
     * 2 ^ 31 - 1 = 2147483647
     *
     * radix sort
     * 当d = 10时， 最多遍历10次，复杂度 10N， 因为n * log n， n为int，
     * 所以n * log n <= 32 n(这个分析时不对的，n*logn 是不能这样用的，但是可以看出常数影响很大）
     */
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;

        int di = 100;
        ArrayList<Integer>[] buckets = new ArrayList[di];
        for (int k = 0; k < di; k++) buckets[k] = new ArrayList();

        for (int i = 1; i <= 1000000000; i *= di) {
            for (int j = 0; j < nums.length; j++) {
                buckets[(nums[j] / i) % di].add(nums[j]);
            }
            int j = 0;
            for (ArrayList bucket : buckets) {
                for (Object num : bucket) {
                    nums[j++] = (Integer)num;
                }
                bucket.clear();
            }
        }
        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i] - nums[i - 1]);
        }
        return max;
    }

}
