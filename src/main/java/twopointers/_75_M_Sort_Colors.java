package twopointers;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/sort-colors/
 *
 * Author:   softtwilight
 * Date:     2020/05/16 22:41
 */
public class _75_M_Sort_Colors {
    private static final _75_M_Sort_Colors instance = new _75_M_Sort_Colors();

    public static void main(String[] args) {
//        int[] nums = {1, 0, 0};
        int[] nums = {2,0,0,0,0,1,2,2};
        instance.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }


    /**
     * 参考别人的， 这个就太简单了。。。
     * 记录0，1， 2的个数， 然后重新赋值。
     * 这是不同于排序的思路。
     */
    public void sortColors2(int[] nums) {
        int colors = 3;
        int[] buckets = new int[colors];
        for(int num : nums) buckets[num]++;
        for(int p = 0, val = 0; val < colors; val++) {
            for(int count = 0; count < buckets[val]; count++) {
                nums[p++] = val;
            }
        }
    }

    /**
     * 这个解法也相当机智，很像堆积木
     *
     * 对有限个数，0， 1， 2的数组都以用这个和上面的类似方法
     *
     */
    void sortColors3(int A[]) {
        int n0 = -1, n1 = -1, n2 = -1;
        for (int i = 0; i < A.length; ++i) {
            if (A[i] == 0) {
                A[++n2] = 2; A[++n1] = 1; A[++n0] = 0;
            } else if (A[i] == 1) {
                A[++n2] = 2; A[++n1] = 1;
            } else if (A[i] == 2) {
                A[++n2] = 2;
            }
        }
    }


    /**
     * 这个本质上是维护三个指针， start相当于low 0的指针，
     * i 相当于 mid 1的指针， end 相当于 high 2的指针。
     */
    public void sortColors4(int[] nums) {
        int start = 0, end = nums.length - 1;
        for(int i = start; i <= end;){
            if(nums[i] == 0){
                swap(nums, i, start);
                start++;
                i++;
            }
            else if(nums[i] == 2){
                swap(nums, i, end);
                end--;
            }
            else i++;
        }
    }
    /**
     * in place sort， 同时要求one-pass。
     * 直观上觉得用快排的思路，其中1比较难处理
     * 0 ms	+ 39.6 MB
     *
     */
    public void sortColors(int[] nums) {

        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {

            // 找下一个不等于=0的数
            while (lo < hi && nums[lo] == 0) {
                lo++;
            }
            // 找下一个等于0的数
            while (lo < hi && nums[hi] != 0) {
                hi--;
            }
            if (nums[hi] < nums[lo]) {
                swap(nums, lo, hi);
                lo++;
            }
            hi--;
        }
        if (nums[lo] == 0) lo++;
        hi = nums.length - 1;
        while (lo < hi) {
            while (lo < hi && nums[lo] == 1) {
                lo++;
            }
            while (lo < hi && nums[hi] == 2) {
                hi--;
            }
            if ( nums[hi] < nums[lo]) {
                swap(nums, lo, hi);
                lo++;
            }
            hi--;
        }
    }

    void swap(int[] nums, int lo, int hi) {
        int temp = nums[lo];
        nums[lo] = nums[hi];
        nums[hi] = temp;
    }


}
