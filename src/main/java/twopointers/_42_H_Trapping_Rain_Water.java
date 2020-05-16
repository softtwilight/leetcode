package twopointers;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 * Author:   softtwilight
 * Date:     2020/05/16 22:30
 */
public class _42_H_Trapping_Rain_Water {
    private static final _42_H_Trapping_Rain_Water instance = new _42_H_Trapping_Rain_Water();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 这道题做过两次了，知道套路就很好写代码了。
     *
     * 找到最大值的点，然后在最大值左边，以leftMax蓄水
     * 在最大值右边以rightMax蓄水。
     * 然后可以把这个转换为双指针，leftMax < rightMax <= max, 左边蓄水，移动左边
     *
     * 少遍历一次。
     */
    public int trap(int[] height) {
        int lo = 0, hi = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int result = 0;

        while (lo < hi) {
            leftMax = Math.max(leftMax, height[lo]);
            rightMax = Math.max(rightMax, height[hi]);
            if (leftMax > rightMax) {
                result += rightMax - height[hi];
                hi--;
            } else {
                result += leftMax - height[lo];
                lo++;
            }
        }
        return result;
    }
}
