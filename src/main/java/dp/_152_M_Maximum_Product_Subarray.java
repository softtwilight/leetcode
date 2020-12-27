package dp;

/**
 * Author:   softtwilight
 * Date:     2020/12/27 22:25
 */
public class _152_M_Maximum_Product_Subarray {
    private static final _152_M_Maximum_Product_Subarray instance = new _152_M_Maximum_Product_Subarray();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 解得有点复杂，记录nums[i : ~]的max 和 min值
     * 然后根据当前值的正负号来计算max和min
     */
    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;

        int[] max = new int[nums.length];
        max[nums.length - 1] = nums[nums.length - 1];
        int[] min = new int[nums.length];
        min[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > 0) {
                if (max[i + 1] <= 0) {
                    max[i] = nums[i];
                } else {
                    max[i] = nums[i] * max[i + 1];
                }
                if (min[i + 1] >= 0) {
                    min[i] = nums[i];
                } else {
                    min[i] = min[i + 1] * nums[i];
                }

            } else if (nums[i] == 0) {
                max[i] = 0;
                min[i] = 0;
            } else {
                if (max[i + 1] > 0) {
                    min[i] = nums[i] * max[i + 1];
                } else {
                    min[i] = nums[i];
                }
                if (min[i + 1] <= 0) {
                    max[i] = nums[i] * min[i + 1];
                } else {
                    max[i] = nums[i];
                }
            }
        }
        int result = max[0];
        for (int i = 0; i < max.length; i++) {
            result = Math.max(result, max[i]);
        }
        return result;
    }

}
