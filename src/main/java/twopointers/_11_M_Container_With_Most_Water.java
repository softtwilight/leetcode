package twopointers;

/**
 * https://leetcode.com/problems/container-with-most-water/
 * Author:   softtwilight
 * Date:     2020/05/15 23:18
 */
public class _11_M_Container_With_Most_Water {
    private static final _11_M_Container_With_Most_Water instance = new _11_M_Container_With_Most_Water();

    public static void main(String[] args) {
        System.out.println(instance.maxArea(new int[]{1, 2, 2, 3}));

    }

    /**
     * 双指针，只需要搞清楚移动指针的条件就可以了
     * 要找到更大的值，就不能移动大的指针，因为大指针从a -> b
     * 1. 如果height[b] > lower, 容器还是会取小指针的高度， 而宽度减1
     * 2. 如果height[b] <= lower，则result会更小
     *
     */
    public int maxArea(int[] height) {
        int lo = 0, hi = height.length - 1;
        int result = 0;
        while (lo < hi) {
            int low = Math.min(height[lo], height[hi]);
            result = Math.max(result, low * (hi - lo));
            if (height[lo] < height[hi]) {
                lo++;
            } else {
                hi--;
            }
        }
        return result;
    }
}
