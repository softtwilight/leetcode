package banarysearch;

/**
 *
 * Given an array of n positive integers and a positive integer s, find the minimal
 * length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 *
 * Example:
 *
 * Input: s = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 *
 * Explanation: the subarray [4,3] has the minimal length under the problem constraint.
 * Follow up:
 * If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 *
 * Author:   softtwilight
 * Date:     2020/05/01 21:31
 */
public class _209_Minimum_Size_Subarray_Sum {

    public static void main(String[] args) {
        int[] input = {2,3,1,2,4,3};
        System.out.println(minSubArrayLen(7, input));
    }


    /**
     * 1 ms	+ 39.4 MB
     * 滑动窗口的解法，因为是连续数组，所以我们用两个指针分别来代表数组的头尾。
     * hi 依次增大sum， lo 依次减少sum， 当sum 大于目标时，我们记录数组长度
     * 然后增大lo一直到sum 小于 目标。
     * （ps 第一次提交的时候忽略了所有长度加起来都不够目标值的情况，应返回0。
     *      为什么会漏掉呢，还是思考不够全面，比如在while这里，只考虑了sum >= s,
     *      漏掉了sum 总是小于 s的情况，这时候我们选的默认值为nums.length返回是不合理的）
     * 我们可以看到最多会遍历两次数组，hi 和 lo分别遍历依次， 复杂度为O（n）
     *
     * 题目还需要O(n log n)这样的复杂度。
     */
    public static int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int lo = 0, hi = 0;
        int sum = 0;
        int result = nums.length + 1;
        while (hi < nums.length) {
            sum += nums[hi++];
            while (sum >= s) {
                result = Math.min(result, hi - lo);
                sum -= nums[lo++];
            }
        }
        return result == nums.length + 1 ? 0 : result;
    }
}
