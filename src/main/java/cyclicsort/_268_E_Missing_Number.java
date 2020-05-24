package cyclicsort;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/missing-number/
 *
 * Author:   softtwilight
 * Date:     2020/05/23 22:58
 */
public class _268_E_Missing_Number {
    private static final _268_E_Missing_Number instance = new _268_E_Missing_Number();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 要求constant extra space complexity,我们记录整个数组就是不可行的
     *
     * 1 ms	39.7 MB
     * 还是先记录在整个数组吧
     */
    public int missingNumber(int[] nums) {
        int[] all = new int[nums.length + 1];
        Arrays.fill(all, -1);
        for (int i = 1; i < all.length; i++) {
            all[nums[i - 1]] = 0;
        }
        for (int i = 0; i < all.length; i++) {
            if (all[i] == -1) {
                return i;
            }
        }
        return 0;
    }

    /**
     * index 和值取^, 如果都存在 也就是 index 4 和 value 4存在， 那么他们的^ = 0
     * 只会剩下一个数，就是miss的数。
     *
     * ​index 0 1 2 3
     * value 0 1 3 4
     *
     * missing =4∧(0∧0)∧(1∧1)∧(2∧3)∧(3∧4)
     *         =(4∧4)∧(0∧0)∧(1∧1)∧(3∧3)∧2
     *         =0∧0∧0∧0∧2
     *         =2
     * ​
     *
     */
    public int missingNumber2(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }


    /**
     * cyclic sort
     */
    public int missingNumber3(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] < nums.length && nums[nums[i]] != nums[i]) {
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i]) {
                return i;
            }
        }
        return nums.length;
    }
}
