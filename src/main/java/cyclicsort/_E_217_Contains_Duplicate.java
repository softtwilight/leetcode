package cyclicsort;

import java.util.HashSet;
import java.util.Set;

/**
 * Author:   softtwilight
 * Date:     2020/05/24 21:47
 */
public class _E_217_Contains_Duplicate {
    private static final _E_217_Contains_Duplicate instance = new _E_217_Contains_Duplicate();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 这是一道很简单的题，直接用Set就可以解了
     * 为了锻炼一下cyclic sort的写法， 写了复杂的方式，也就是说最大程度的利用
     * 已经给的数组来判断重复，在数组范围意外在利用Set。
     *
     * 已经给的数组长度是l， 然后可以用l长度来表示一个范围 k ～ k + l - 1;
     * 并不一定是从0开始， 判断一下最大值，最小值， 然后选取中间的点也是可以的，
     * 这样预估数据来最大利用空间。
     * 这里就不实现来了
     *
     * Runtime: 4 ms, faster than 82.11%
     * Memory Usage: 42.6 MB, less than 96.55%
     */
    public boolean containsDuplicate(int[] nums) {

        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < nums.length && nums[i] >= 0) {
                while (i < nums.length && nums[i] != i && nums[i] < nums.length && nums[i] >= 0) {
                    if (nums[i] == nums[nums[i]]) {
                        return true;
                    }
                    int temp = nums[nums[i]];
                    nums[nums[i]] = nums[i];
                    nums[i] = temp;
                }
            } else {
                if (s.contains(nums[i])) {
                    return true;
                } else {
                    s.add(nums[i]);
                }
            }
        }
        return false;
    }
}
