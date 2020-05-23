package array;

/**
 * https://leetcode.com/problems/first-missing-positive/
 *
 * Author:   softtwilight
 * Date:     2020/05/23 23:32
 */
public class _41_H_First_Missing_Positive {
    private static final _41_H_First_Missing_Positive instance = new _41_H_First_Missing_Positive();

    public static void main(String[] args) {

        System.out.println(instance.firstMissingPositive(new int[]{1}));
    }

    /**
     * 0 ms	37.4 MB
     * n的时间复杂度，但是space complexity is not constant.
     *
     */
    public int firstMissingPositive(int[] nums) {
        int[] memo = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums.length || nums[i] <= 0) {
                continue;
            }
            memo[nums[i] - 1] = 1;
        }
        for (int i = 0; i < memo.length; i++) {
            if (memo[i] == 0) {
                return i + 1;
            }
        }
        return 1;
    }


    /**
     * 本质上是利用给予的数组空间进行替换，因为是整数，保证从1开始，因为长度为n，所以缺的数最大为n + 1
     * 这样保证了在数组空间内替换，能确定找到解。
     * 比如1就交换到坐标0，2到坐标1
     * 然后遍历， 不符合的就是第一个missing的数。
     */
    public int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            while(nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1])
                swap(nums, i, nums[i] - 1);
        }
        for(int i = 0; i < n; i++)
            if(nums[i] != i + 1)
                return i + 1;
        return n + 1;
    }

    private void swap(int[] nums, int i, int j) {
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }
}
