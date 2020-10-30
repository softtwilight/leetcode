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

        int max = 0;
        for (int n : nums) {
            max = Math.max(max, n);
        }
        for (int i = 1; i <= max; i <<= di) {
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
        max = 0;
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i] - nums[i - 1]);
        }
        return max;
    }

    /**
     * bucket sort.
     * 关键的点是这样的。
     * 找到max， min
     * 然后最平均分配的情况下，gap = （max - min) / n - 1;
     * 那么我们用gap大小当bucket， 在桶内的元素最大gap是小于gap的
     * 所以gap一定是存在于bucket 和 bucket 之间。
     * 这时候我们找最大值，只需要找之前的gap就行了。
     */
    public int maximumGap2(int[] nums) {
        int n = nums.length;
        if(n < 2) return 0;
        int min = nums[0];
        int max = nums[0];
        for(int i = 1;i < n;i++){
            if(min > nums[i]) min = nums[i];
            if(max < nums[i]) max = nums[i];
        }

        int gap = (max-min)/(n-1);
        if(gap == 0) gap++;
        int len = (max-min)/gap+1;
        int [] tmax = new int [len];
        int [] tmin = new int [len];

        for(int i = 0;i < n;i++){
            int index = (nums[i]-min)/gap;
            if(nums[i] > tmax[index]) tmax[index] = nums[i];
            if(tmin[index] == 0 || nums[i] < tmin[index]) tmin[index] = nums[i];
        }
        int myMax = 0;
        for(int i = 0;i < len;i++){
            if(myMax < tmin[i]-min) myMax = tmin[i]-min;
            if(tmax[i] != 0) min = tmax[i];
        }
        return myMax;
    }

}
