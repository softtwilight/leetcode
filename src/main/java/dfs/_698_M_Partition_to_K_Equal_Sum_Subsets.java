package dfs;

import java.util.Arrays;

/**
 * Author:   softtwilight
 * Date:     2021/01/19 23:26
 */
public class _698_M_Partition_to_K_Equal_Sum_Subsets {
    private static final _698_M_Partition_to_K_Equal_Sum_Subsets instance = new _698_M_Partition_to_K_Equal_Sum_Subsets();

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        System.out.println(instance.canPartitionKSubsets(nums, 4));
    }

    /**
     *
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
//        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % k != 0) return false;
        int subSum = sum / k;
        subsets = subSum;
        boolean[] used = new boolean[nums.length];
        return dp(nums, used, subSum, k);
    }

    int subsets;

    private boolean dp(int[] nums, boolean[] used, int subSum, int k) {
        if (k == 0) return subSum == subsets;
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                if (subSum == nums[i]) {
                    used[i] = true;
                    return dp(nums, used, subsets, k - 1);
                } else {
                    used[i] = true;
                    boolean result = dp(nums, used, subSum - nums[i], k);
                    if (result) return true;
                    used[i] = false;
                }
            }
        }
        return false;
    }
}
