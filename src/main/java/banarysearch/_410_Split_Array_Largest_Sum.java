package banarysearch;

/**
 * Given an array which consists of non-negative integers and an integer m,
 * you can split the array into m non-empty continuous subarrays.
 * Write an algorithm to minimize the largest sum among these m subarrays.
 *
 * Note:
 * If n is the length of array, assume the following constraints are satisfied:
 *
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 *
 * Examples:
 *
 * Input:
 * nums = [7,2,5,10,8]
 * m = 2
 *
 * Output:
 * 18
 *
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 *
 * Author:   softtwilight
 * Date:     2020/05/04 22:59
 */
public class _410_Split_Array_Largest_Sum {

    public static void main(String[] args) {
        int[] input = {3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6};
        _410_Split_Array_Largest_Sum instance = new _410_Split_Array_Largest_Sum();
        System.out.println(instance.splitArray(input, 20));
    }

    /**
     * 二分查找到值后，返回坐标和返回坐标的前一个值都是需要考虑的。
     * 应该会用到回溯，所以要用到递归，递归的一个参数就是sumed， 一个参数是i；
     * test还没有通过， 超时了。
     */
    public int splitArray(int[] nums, int m) {
        int[] sums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) sums[i] = nums[i];
            else sums[i] = nums[i] + sums[i - 1];
        }

        int i = 0;
        int sumed = 0;
        helper(sums, sumed, i, m, 0);
        return rRe;
    }

    int rRe = Integer.MAX_VALUE;

    private void helper(int[] sums, int sumed, int lo, int leftm, int result) {

        if (leftm == 1) {
            result = Math.max(sums[sums.length - 1] - sumed, result);
            rRe = Math.min(rRe,result);
            return;
        }
        int average = (sums[sums.length - 1] - sumed) / leftm;
        int[] res = search(sums, sumed + average, lo, sums.length - 1);

        int s = res[1];
        if (res[0] == 1 || s < sums.length) {
            int temp = result;
            temp = Math.max(result, sums[s] - sumed);
            helper(sums, sums[s], s + 1, leftm - 1, temp);
        }
        if (res[0] == 1) {return;}


        s--;
        if (s >= 0) {
            result = Math.max(result, sums[s] - sumed);
            helper(sums, sums[s], s + 1, leftm - 1, result);
        }

    }




    private int[] search(int[] sums, int target, int lo, int hi) {

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (sums[mid] == target) {
                return new int[]{1, mid};
            } else if (sums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return new int[]{0, lo};
    }


}
